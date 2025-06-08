package com.tratra.tratra_app.controller;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import com.tratra.tratra_app.entity.PasswordResetToken;
import com.tratra.tratra_app.entity.User;
import com.tratra.tratra_app.entity.Activity;
import com.tratra.tratra_app.service.PasswordResetTokenService;
import com.tratra.tratra_app.service.EmailService;
import com.tratra.tratra_app.service.UserService;
import com.tratra.tratra_app.service.ActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private EmailService emailService;

    private static final String AVATAR_UPLOAD_DIR = "uploads/avatars/";

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String fullName,
                               @RequestParam String email,
                               Model model) {
        if (userService.findByUsername(username).isPresent()) {
            model.addAttribute("error", "El usuario ya existe.");
            return "register";
        }
        userService.registerUser(username, password, fullName, email);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/creador")
    public String mostrarCreador() {
        return "creador";
    }

    @GetMapping("/home")
    public String showHome(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        model.addAttribute("fullName", user.getFullName());
        model.addAttribute("avatarPath", user.getAvatarPath());

        List<Activity> activities = activityService.findAllByUser(user);
        model.addAttribute("activities", activities);

        return "home";
    }

    @GetMapping("/profile")
    public String showProfile(@RequestParam(required = false) String startDate,
                              @RequestParam(required = false) String endDate,
                              Model model,
                              Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username).orElse(null);

        if (user == null) {
            return "redirect:/login";
        }

        // Datos básicos
        model.addAttribute("user", user);
        model.addAttribute("avatarPath", user.getAvatarPath());

        // Estadísticas
        model.addAttribute("totalDistanceKm", activityService.getKmTotales(user));
        model.addAttribute("weeklyDistanceKm", activityService.getKmSemana(user));
        model.addAttribute("monthlyDistanceKm", activityService.getKmMes(user));
        model.addAttribute("totalElevationGain", activityService.getDesnivelTotal(user));
        model.addAttribute("weeklyElevationGain", activityService.getDesnivelSemana(user));
        model.addAttribute("monthlyElevationGain", activityService.getDesnivelMes(user));

        // Records
        model.addAttribute("recordDistanceKm", activityService.getRecordDistancia(user));
        model.addAttribute("recordElevationGain", activityService.getRecordDesnivel(user));

        // Datos para gráficos
        model.addAttribute("diasSemana", activityService.getDiasSemana(user));
        model.addAttribute("kmsSemanaDia", activityService.getKmsPorDiaSemana(user));
        model.addAttribute("elevationGainSemanaDia", activityService.getDesnivelPorDiaSemana(user)); // corregido

        // Filtro de fechas
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "profile";
    }

    @GetMapping("/profile/edit")
    public String showEditProfileForm(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        model.addAttribute("user", user);
        return "edit_profile";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(@RequestParam String fullName,
                                @RequestParam(required = false) String password,
                                @RequestParam(required = false) String croppedImage,
                                Authentication authentication,
                                RedirectAttributes redirectAttributes) {
        String username = authentication.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        user.setFullName(fullName);

        if (password != null && !password.isBlank()) {
            userService.updatePassword(user, password);
        }

        if (croppedImage != null && !croppedImage.isBlank()) {
            try {
                String base64Image = croppedImage.split(",")[1];
                byte[] imageBytes = Base64.getDecoder().decode(base64Image);

                String avatarFilename = userService.saveUserAvatar(user, imageBytes);
                if (avatarFilename != null) {
                    user.setAvatarPath("/" + AVATAR_UPLOAD_DIR + avatarFilename);
                }
            } catch (Exception e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "Hubo un problema al guardar el avatar.");
                return "redirect:/profile/edit";
            }
        }

        userService.save(user);
        redirectAttributes.addFlashAttribute("success", "Perfil actualizado con éxito.");
        return "redirect:/home";
    }

    @GetMapping("/some-path")
    public String showHomePage(Model model, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        model.addAttribute("user", user);
        return "home";
    }

    // Recuperación de contraseña

    @GetMapping("/recover")
    public String showRecoverForm() {
        return "recover";
    }

    @PostMapping("/recover")
    public String processRecover(@RequestParam String email, Model model) {
        Optional<User> userOpt = userService.findByEmail(email);

        if (userOpt.isEmpty()) {
            model.addAttribute("error", "No existe ningún usuario con ese email.");
            return "recover";
        }

        User user = userOpt.get();
        PasswordResetToken token = passwordResetTokenService.createToken(user);

        emailService.sendPasswordResetEmail(user, token.getToken());
        model.addAttribute("message", "Se ha enviado un email para restablecer la contraseña.");
        return "recover";
    }

    @GetMapping("/recover/change-password")
    public String showChangePasswordForm(@RequestParam String token, Model model) {
        Optional<PasswordResetToken> tokenOpt = passwordResetTokenService.findByToken(token);

        if (tokenOpt.isEmpty() || tokenOpt.get().isExpired()) {
            model.addAttribute("error", "Token inválido o expirado.");
            return "recover";
        }

        model.addAttribute("token", token);
        return "change_password";
    }

    @PostMapping("/recover/change-password")
    public String processChangePassword(@RequestParam String token,
                                        @RequestParam String newPassword,
                                        Model model) {
        Optional<PasswordResetToken> tokenOpt = passwordResetTokenService.findByToken(token);

        if (tokenOpt.isEmpty() || tokenOpt.get().isExpired()) {
            model.addAttribute("error", "Token inválido o expirado.");
            return "change_password";
        }

        User user = tokenOpt.get().getUser();
        userService.updatePassword(user, newPassword);
        passwordResetTokenService.deleteToken(tokenOpt.get());

        model.addAttribute("message", "Contraseña cambiada con éxito. Ya puedes iniciar sesión.");
        return "login";
    }

    private String getFileExtension(String filename) {
        if (filename == null) return "";
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex == -1) ? "" : filename.substring(dotIndex + 1);
    }
}
