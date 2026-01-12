package com.auth_service.dto;

import lombok.Data;

@Data
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private long expiresIn;
    private String role;
}