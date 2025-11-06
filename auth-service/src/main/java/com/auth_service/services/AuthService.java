package com.auth_service.services;

import com.auth_service.dto.ApiResponse;
import com.auth_service.dto.AuthRequest;
import com.auth_service.entities.User;
import com.auth_service.utils.JwtUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public ApiResponse login(
            @NonNull AuthRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        User user = (User) authentication.getPrincipal();
        String token = jwtUtil.generateToken(user);

        return ApiResponse.builder()
                .message("User logged in successfully.")
                .data(token)
                .statusCode(HttpStatus.OK)
                .build();
    }
}
