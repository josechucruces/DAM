package com.tratra.tratra_app.service;

import org.springframework.transaction.annotation.Transactional;
import com.tratra.tratra_app.entity.Activity;
import com.tratra.tratra_app.entity.User;
import com.tratra.tratra_app.repository.ActivityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

@Service
public class ActivityService {

    private static final String GPX_UPLOAD_DIR = "uploads/gpx/";

    @Autowired
    private ActivityRepository activityRepository;

    public void saveActivity(String name, String description, String dateStr, String activityType, MultipartFile gpxFile, User user) {
        try {
            Path uploadPath = Paths.get(GPX_UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            if (gpxFile == null || gpxFile.isEmpty()) {
                throw new IllegalArgumentException("Archivo GPX no puede estar vacío");
            }

            String originalFilename = Path.of(gpxFile.getOriginalFilename()).getFileName().toString();
            String filename = System.currentTimeMillis() + "_" + originalFilename.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");

            Path filePath = uploadPath.resolve(filename);
            Files.copy(gpxFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            LocalDate date = LocalDate.parse(dateStr);

            // Leer coordenadas y elevaciones del archivo
            List<double[]> points = parseGpxFileToCoordinatesWithElevation(filePath.toAbsolutePath().toString());

            double distanceKm = calculateTotalDistance(points);
            double elevationGain = calculateElevationGain(points);

            Activity activity = new Activity();
            activity.setName(name);
            activity.setDescription(description);
            activity.setDate(date);
            activity.setGpxFilePath(filePath.toAbsolutePath().toString());
            activity.setUser(user);
            activity.setActivityType(activityType);
            activity.setDistanceKm(distanceKm);
            activity.setElevationGain(elevationGain);

            activityRepository.save(activity);

        } catch (IOException e) {
            throw new RuntimeException("Error guardando el archivo GPX", e);
        } catch (Exception e) {
            throw new RuntimeException("Error procesando la actividad: " + e.getMessage(), e);
        }
    }
    @Transactional
    public void deleteActivityAndGpx(Long activityId) throws Exception {
        Activity activity = activityRepository.findById(activityId)
            .orElseThrow(() -> new Exception("Actividad no encontrada con ID: " + activityId));

        String gpxFilePath = activity.getGpxFilePath();
        if (gpxFilePath != null && !gpxFilePath.isEmpty()) {
            Path path = Paths.get(gpxFilePath);
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                // Opcional: loggear error, pero no detener el proceso
                System.err.println("No se pudo borrar archivo GPX: " + e.getMessage());
            }
        }

        activityRepository.delete(activity);
    }

    public List<Activity> findByUsername(String username) {
        return activityRepository.findByUserUsername(username);
    }

    public Optional<Activity> findById(Long id) {
        return activityRepository.findById(id);
    }

    public List<Activity> findAllByUser(User user) {
        return activityRepository.findAllByUser(user);
    }
    public Set<DayOfWeek> getDiasSemana(User user) {
        List<Activity> activities = findAllByUser(user);
        return activities.stream()
                .map(a -> a.getDate().getDayOfWeek())
                .collect(Collectors.toSet());
    }

    // Método extendido para obtener latitud, longitud y elevación
    public List<double[]> parseGpxFileToCoordinatesWithElevation(String gpxFilePath) {
        List<double[]> coordinates = new ArrayList<>();
        try {
            File gpxFile = new File(gpxFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            var dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(gpxFile);

            doc.getDocumentElement().normalize();

            NodeList trkptList = doc.getElementsByTagName("trkpt");

            for (int i = 0; i < trkptList.getLength(); i++) {
                Node trkptNode = trkptList.item(i);
                if (trkptNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element trkptElement = (Element) trkptNode;
                    double lat = Double.parseDouble(trkptElement.getAttribute("lat"));
                    double lon = Double.parseDouble(trkptElement.getAttribute("lon"));
                    double ele = 0.0;

                    NodeList children = trkptElement.getElementsByTagName("ele");
                    if (children.getLength() > 0) {
                        ele = Double.parseDouble(children.item(0).getTextContent());
                    }

                    coordinates.add(new double[]{lat, lon, ele});
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error leyendo el archivo GPX: " + e.getMessage(), e);
        }
        return coordinates;
    }

    // Calcula distancia total en kilómetros usando la fórmula de Haversine
    public double calculateTotalDistance(List<double[]> coords) {
        double total = 0.0;
        for (int i = 1; i < coords.size(); i++) {
            total += haversine(coords.get(i - 1), coords.get(i));
        }
        return total;
    }

    // Fórmula de Haversine para calcular distancia entre dos puntos geográficos
    private double haversine(double[] p1, double[] p2) {
        double R = 6371.0; // radio de la Tierra en km
        double lat1 = Math.toRadians(p1[0]);
        double lon1 = Math.toRadians(p1[1]);
        double lat2 = Math.toRadians(p2[0]);
        double lon2 = Math.toRadians(p2[1]);

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

    // Calcula desnivel positivo (suma de todos los ascensos)
    public double calculateElevationGain(List<double[]> coords) {
        double gain = 0.0;
        for (int i = 1; i < coords.size(); i++) {
            double diff = coords.get(i)[2] - coords.get(i - 1)[2];
            if (diff > 0) {
                gain += diff;
            }
        }
        return gain;
    }


    // ----- NUEVOS MÉTODOS PARA MÉTRICAS Y GRÁFICOS ------

    // Obtiene todas las actividades de un usuario
    private List<Activity> getActivities(User user) {
        return activityRepository.findAllByUser(user);
    }

    // Filtra actividades entre fecha start y end (inclusive)
    private List<Activity> filterByDateRange(List<Activity> activities, LocalDate start, LocalDate end) {
        return activities.stream()
                .filter(a -> !a.getDate().isBefore(start) && !a.getDate().isAfter(end))
                .collect(Collectors.toList());
    }

    // Kms totales de un usuario
    public double getKmTotales(User user) {
        List<Activity> activities = getActivities(user);
        return activities.stream()
                .mapToDouble(Activity::getDistanceKm)
                .sum();
    }

    // Kms en los últimos 7 días (semana actual)
    public double getKmSemana(User user) {
        LocalDate hoy = LocalDate.now();
        LocalDate hace7Dias = hoy.minusDays(6);
        List<Activity> activities = filterByDateRange(getActivities(user), hace7Dias, hoy);
        return activities.stream()
                .mapToDouble(Activity::getDistanceKm)
                .sum();
    }

    // Kms en el mes actual
    public double getKmMes(User user) {
        LocalDate hoy = LocalDate.now();
        LocalDate inicioMes = hoy.withDayOfMonth(1);
        List<Activity> activities = filterByDateRange(getActivities(user), inicioMes, hoy);
        return activities.stream()
                .mapToDouble(Activity::getDistanceKm)
                .sum();
    }

    // Desnivel total (suma de elevaciones positivas)
    public double getDesnivelTotal(User user) {
        List<Activity> activities = getActivities(user);
        return activities.stream()
                .mapToDouble(Activity::getElevationGain)
                .sum();
    }

    // Desnivel en la última semana
    public double getDesnivelSemana(User user) {
        LocalDate hoy = LocalDate.now();
        LocalDate hace7Dias = hoy.minusDays(6);
        List<Activity> activities = filterByDateRange(getActivities(user), hace7Dias, hoy);
        return activities.stream()
                .mapToDouble(Activity::getElevationGain)
                .sum();
    }

    // Desnivel en el mes actual
    public double getDesnivelMes(User user) {
        LocalDate hoy = LocalDate.now();
        LocalDate inicioMes = hoy.withDayOfMonth(1);
        List<Activity> activities = filterByDateRange(getActivities(user), inicioMes, hoy);
        return activities.stream()
                .mapToDouble(Activity::getElevationGain)
                .sum();
    }

    // Actividad con mayor distancia (récord)
    public Activity getRecordDistancia(User user) {
        return getActivities(user).stream()
                .max(Comparator.comparingDouble(Activity::getDistanceKm))
                .orElse(null);
    }

    // Actividad con mayor desnivel (récord)
    public Activity getRecordDesnivel(User user) {
        return getActivities(user).stream()
                .max(Comparator.comparingDouble(Activity::getElevationGain))
                .orElse(null);
    }

    // Nombres de los días de la semana en español, abreviados
    public List<String> getDiasSemana() {
        return Arrays.asList("Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom");
    }

    // Obtener kms por día (Lun-Dom) en la semana actual
    public List<Double> getKmsPorDiaSemana(User user) {
        LocalDate hoy = LocalDate.now();

        LocalDate lunes = hoy.with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 1);

        Map<LocalDate, Double> kmsPorDia = new HashMap<>();
        for (int i = 0; i < 7; i++) {
            kmsPorDia.put(lunes.plusDays(i), 0.0);
        }

        List<Activity> activities = filterByDateRange(getActivities(user), lunes, lunes.plusDays(6));

        for (Activity a : activities) {
            kmsPorDia.put(a.getDate(), kmsPorDia.getOrDefault(a.getDate(), 0.0) + a.getDistanceKm());
        }

        List<Double> resultado = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            resultado.add(kmsPorDia.get(lunes.plusDays(i)));
        }
        return resultado;
    }

    // Obtener desnivel por día (Lun-Dom) en la semana actual
    public List<Double> getDesnivelPorDiaSemana(User user) {
        LocalDate hoy = LocalDate.now();

        LocalDate lunes = hoy.with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 1);

        Map<LocalDate, Double> desnivelPorDia = new HashMap<>();
        for (int i = 0; i < 7; i++) {
            desnivelPorDia.put(lunes.plusDays(i), 0.0);
        }

        List<Activity> activities = filterByDateRange(getActivities(user), lunes, lunes.plusDays(6));

        for (Activity a : activities) {
            desnivelPorDia.put(a.getDate(), desnivelPorDia.getOrDefault(a.getDate(), 0.0) + a.getElevationGain());
        }

        List<Double> resultado = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            resultado.add(desnivelPorDia.get(lunes.plusDays(i)));
        }
        return resultado;
    }

}
