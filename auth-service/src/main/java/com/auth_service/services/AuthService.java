package com.auth_service.services;

import com.auth_service.dto.ApiResponse;
import com.auth_service.dto.AuthRequest;
import com.auth_service.dto.RegisterRequest;
import com.auth_service.entities.Role;
import com.auth_service.entities.User;
import com.auth_service.repositories.RoleRepository;
import com.auth_service.repositories.UserRepository;
import com.auth_service.utils.JwtUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Service class for handling authentication logic, including user login and registration.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    /**
     * Authenticates a user and generates a JWT token upon successful authentication.
     *
     * @param request the authentication request
     * @return ApiResponse containing the JWT token
     */
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

    /**
     * Registers a new user with the default 'TENANT' role.
     *
     * @param request the registration request
     * @return ApiResponse containing the registered user details
     */
    public ApiResponse register(
            @NonNull RegisterRequest request) {
        boolean isExists = this.userRepository.existsByEmail(request.getEmail());
        if (isExists) {
            return ApiResponse.builder()
                    .message("User already exists with this email: " + request.getEmail())
                    .statusCode(HttpStatus.CONFLICT)
                    .build();
        }
        Role role = this.roleRepository.findByName("TENANT")
                .orElseThrow(() -> new RuntimeException("Default role 'TENANT' not found. Please contact administrator."));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        User createdUser = this.userRepository.save(user);
        return ApiResponse.builder()
                .message("User registered successfully")
                .data(createdUser)
                .statusCode(HttpStatus.CREATED)
                .build();
    }
}
