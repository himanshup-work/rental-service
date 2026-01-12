package com.admin_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class OwnerRequestResponse {
    private Long id;
    private Long userId;
    private String businessName;
    private String documentId;
    private String status;
    private Instant requestedAt;
}

