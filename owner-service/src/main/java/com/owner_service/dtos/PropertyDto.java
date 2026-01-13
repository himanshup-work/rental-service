package com.owner_service.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class PropertyDto {
    private Long id;
    private String owner_id;
    private String title;
    private String description;
    private String address;
    private String property_type;
    private Long price;
    private String status;
    private Instant created_at;
    private Instant updated_at;
}
