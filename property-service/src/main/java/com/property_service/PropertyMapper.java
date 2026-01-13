package com.property_service;

import com.property_service.dtos.PropertyDto;
import com.property_service.entities.Property;

public class PropertyMapper {
    public static Property mapToEntity(PropertyDto property) {
        return Property.builder()
                .id(property.getId())
                .ownerId(property.getOwnerId())
                .title(property.getTitle())
                .description(property.getDescription())
                .address(property.getAddress())
                .propertyType(property.getPropertyType())
                .price(property.getPrice())
                .status(property.getStatus())
                .createdAt(property.getCreatedAt())
                .updatedAt(property.getUpdatedAt())
                .build();
    }

    public static PropertyDto mapToDto(Property property) {
        return PropertyDto.builder()
                .id(property.getId())
                .ownerId(property.getOwnerId())
                .title(property.getTitle())
                .description(property.getDescription())
                .address(property.getAddress())
                .propertyType(property.getPropertyType())
                .price(property.getPrice())
                .status(property.getStatus())
                .createdAt(property.getCreatedAt())
                .updatedAt(property.getUpdatedAt())
                .build();
    }
}
