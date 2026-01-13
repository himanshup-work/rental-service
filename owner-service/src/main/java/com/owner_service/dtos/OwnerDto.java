package com.owner_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {
    private Long id;
    private Long userId;
    private String email;
    private String businessName;
    private String phoneNumber;
    private String address;
    private Instant createdAt;
    private Instant updatedAt;
}
