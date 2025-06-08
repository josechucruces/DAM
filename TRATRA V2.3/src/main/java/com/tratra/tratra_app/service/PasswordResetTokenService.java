package com.tratra.tratra_app.service;

import com.tratra.tratra_app.entity.PasswordResetToken;
import com.tratra.tratra_app.entity.User;
import com.tratra.tratra_app.repository.PasswordResetTokenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetTokenService {

    private static final int EXPIRATION_MINUTES = 60;  // Duración del token

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    public PasswordResetToken createToken(User user) {
        PasswordResetToken token = new PasswordResetToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());  // Genera un token aleatorio
        token.setExpiryDate(LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES));

        return tokenRepository.save(token);
    }

    public Optional<PasswordResetToken> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void deleteToken(PasswordResetToken token) {
        tokenRepository.delete(token);
    }
}
