package com.property_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the owner-service.
 * Intercepts exceptions and returns standardized {@link ApiResponse} objects.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link ResourceNotFoundException} when a requested resource is not found.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ApiResponse.builder()
                .message(ex.getMessage())
                .statusCode(HttpStatus.NOT_FOUND)
                .build(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link IllegalStateException} for bad requests and business logic violations.
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse> illegalStateExceptionHandler(IllegalStateException ex) {
        return new ResponseEntity<>(ApiResponse.builder()
                .message(ex.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST)
                .build(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Generic exception handler for all other unhandled exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> generalExceptionHandler(Exception ex) {
        return new ResponseEntity<>(ApiResponse.builder()
                .message("An unexpected error occurred: " + ex.getMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
