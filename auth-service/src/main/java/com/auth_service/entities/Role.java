package com.auth_service.entities;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enum representing user roles in the system.
 */
public enum Role implements GrantedAuthority {
    TENANT,
    OWNER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
