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

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody AuthRequest request) {
        ApiResponse apiResponse = authService.login(request);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatusCode());
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        ApiResponse apiResponse = authService.register(request);
        return new ResponseEntity<>(apiResponse, apiResponse.getStatusCode());
    }
}
