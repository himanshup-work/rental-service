package com.auth_service.services;

import com.auth_service.entities.RefreshToken;
import com.auth_service.entities.User;
import com.auth_service.repositories.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    @Transactional
    public RefreshToken create(User user) {
        RefreshToken token = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plus(7, ChronoUnit.DAYS))
                .build();
        return repository.save(token);
    }

    public RefreshToken verify(String token) {
        RefreshToken rt = repository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (rt.getExpiryDate().isBefore(Instant.now())) {
            repository.delete(rt);
            throw new RuntimeException("Refresh token expired");
        }
        return rt;
    }
}
