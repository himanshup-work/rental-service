package com.auth_service.services;

import com.auth_service.dto.ApiResponse;
import com.auth_service.dto.AuthRequest;
import com.auth_service.dto.RegisterRequest;
import com.auth_service.dto.TokenResponse;
import com.auth_service.entities.RefreshToken;
import com.auth_service.entities.Role;
import com.auth_service.entities.User;
import com.auth_service.repositories.UserRepository;
import com.auth_service.utils.JwtService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for handling authentication logic, including user login and registration.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder encoder;

    /**
     * Authenticates a user and generates access and refresh tokens.
     *
     * @param request the authentication request
     * @return ApiResponse containing the tokens
     */
    @Transactional
    public ApiResponse login(@NonNull AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.create(user);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken.getToken());
        tokenResponse.setExpiresIn(600000); // Should match application.yaml
        tokenResponse.setRole(user.getRole().name());

        return ApiResponse.builder()
                .message("User logged in successfully.")
                .data(tokenResponse)
                .statusCode(HttpStatus.OK)
                .build();
    }

    /**
     * Registers a new user with the default 'TENANT' role.
     *
     * @param request the registration request
     * @return ApiResponse containing the registered user details
     */
    @Transactional
    public ApiResponse register(@NonNull RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ApiResponse.builder()
                    .message("User already exists with this email: " + request.getEmail())
                    .statusCode(HttpStatus.CONFLICT)
                    .build();
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(Role.TENANT)
                .enabled(true)
                .build();

        User createdUser = userRepository.save(user);

        return ApiResponse.builder()
                .message("User registered successfully")
                .data(createdUser)
                .statusCode(HttpStatus.CREATED)
                .build();
    }
}
