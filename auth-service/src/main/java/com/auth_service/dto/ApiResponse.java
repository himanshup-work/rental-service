package com.auth_service.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponse {
    private String message;
    private Object data;
    private HttpStatus statusCode;
}
