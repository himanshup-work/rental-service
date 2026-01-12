package com.auth_service.controllers;

import com.auth_service.dto.ApiResponse;
import com.auth_service.dto.AuthRequest;
import com.auth_service.dto.RegisterRequest;
import com.auth_service.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling authentication-related requests, such as login and registration.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

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
}
