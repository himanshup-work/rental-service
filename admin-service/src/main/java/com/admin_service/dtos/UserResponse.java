package com.admin_service.dtos;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String role;
    private boolean enabled;
}

