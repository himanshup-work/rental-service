package com.auth_service.exceptions;

import com.auth_service.dto.ApiResponse;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse> handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException){
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Username not found with username: " + usernameNotFoundException.getMessage())
                .statusCode(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatusCode());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse> handleAuthenticationException(
            AuthenticationException exception){
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Authentication Failed: " + exception.getMessage())
                .statusCode(HttpStatus.UNAUTHORIZED)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatusCode());
    }



}
