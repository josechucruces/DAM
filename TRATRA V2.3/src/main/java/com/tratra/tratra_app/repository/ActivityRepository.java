package com.tratra.tratra_app.repository;

import com.tratra.tratra_app.entity.Activity;
import com.tratra.tratra_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    // Buscar actividades por el nombre de usuario (String)
    List<Activity> findByUserUsername(String username);

    // Buscar todas las actividades de un usuario (entidad User)
    List<Activity> findAllByUser(User user);

    // Buscar actividades de un usuario dentro de un rango de fechas
    List<Activity> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);

    // Buscar actividades ordenadas por distancia descendente para un usuario
    List<Activity> findTop1ByUserOrderByDistanceKmDesc(User user);

    // Buscar actividades ordenadas por elevación descendente para un usuario
    List<Activity> findTop1ByUserOrderByElevationGainDesc(User user);
}
