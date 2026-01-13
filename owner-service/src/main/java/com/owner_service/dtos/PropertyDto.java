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
public class PropertyDto {
    private Long id;
    private Long ownerId;
    private String title;
    private String description;
    private String address;
    private String propertyType;
    private Long price;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;
}
