package com.property_service.config;

import com.property_service.exceptions.ApiResponse;
import com.property_service.constants.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for Spring Security, defining filters, roles, and endpoint protections.
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    public static final String[] PUBLIC_URLS = {
            "/auth/**"
    };

    private final JwtAuthenticationFilter authenticationFilter;
    private final JwtAuthenticationEntryPoint entryPoint;

    @Bean
    public AuthenticationManager authenticationManager(
            @NonNull AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            @NonNull HttpSecurity httpSecurity)
        throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();

        httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_URLS).permitAll()
                        .requestMatchers("/property/**").hasAnyAuthority(Role.ADMIN.name(), Role.OWNER.name())
                        .requestMatchers("/property/properties").hasAuthority(Role.TENANT.name())
                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(entryPoint)
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            response.getWriter().write(
                                    objectMapper.writeValueAsString(
                                            ApiResponse.builder()
                                                    .message("Access denied")
                                                    .statusCode(HttpStatus.FORBIDDEN)
                                                    .build()
                                    )
                            );
                        }));

        return httpSecurity.build();
    }

}
