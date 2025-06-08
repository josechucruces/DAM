package com.tratra.tratra_app.service;

import com.tratra.tratra_app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(User user, String token) {
        String resetUrl = "http://localhost:8080/recover/change-password?token=" + token;  // Ajusta el dominio y puerto según tu entorno

        String subject = "Recuperación de contraseña";
        String message = "Hola " + user.getFullName() + ",\n\n"
                + "Haz clic en el siguiente enlace para restablecer tu contraseña:\n"
                + resetUrl + "\n\n"
                + "Si no solicitaste este cambio, ignora este mensaje.";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject(subject);
        email.setText(message);

        mailSender.send(email);
    }
}
