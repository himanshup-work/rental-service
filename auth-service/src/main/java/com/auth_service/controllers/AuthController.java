package com.auth_service.controllers;

import com.auth_service.dto.*;
import com.auth_service.entities.RefreshToken;
import com.auth_service.services.AuthService;
import com.auth_service.services.RefreshTokenService;
import com.auth_service.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling authentication-related requests, such as login and registration.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    /**
     * Authenticates a user and returns a JWT token.
     *
     * @param request the authentication request containing username and password
     * @return a ResponseEntity containing the API response with the JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody AuthRequest request) {
        ApiResponse apiResponse = authService.login(request);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatusCode());
    }

    /**
     * Registers a new user in the system.
     *
     * @param request the registration request containing user details
     * @return a ResponseEntity containing the API response with the registered user details
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        ApiResponse apiResponse = authService.register(request);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatusCode());
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse> refresh(@RequestParam String refreshToken) {
        RefreshToken rt = refreshTokenService.verify(refreshToken);
        String token = jwtService.generateToken(rt.getUser());

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(token);
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setExpiresIn(600000);
        tokenResponse.setRole(rt.getUser().getRole().name());

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Token refreshed successfully")
                        .data(tokenResponse)
                        .statusCode(HttpStatus.OK)
                        .build()
        );
    }

}
