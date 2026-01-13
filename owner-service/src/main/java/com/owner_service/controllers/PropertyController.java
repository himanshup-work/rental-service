package com.owner_service.controllers;

import com.owner_service.dtos.PropertyDto;
import com.owner_service.exceptions.ApiResponse;
import com.owner_service.services.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner/properties")
@RequiredArgsConstructor
@PreAuthorize("hasRole('OWNER')")
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping
    public ResponseEntity<ApiResponse> getMyProperties(
            Authentication authentication
    ) {
        Long ownerId = Long.valueOf(authentication.getName());
        return propertyService.getPropertiesByOwner(ownerId);
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<ApiResponse> getProperty(
            @PathVariable Long propertyId,
            Authentication authentication
    ) {
        Long ownerId = Long.valueOf(authentication.getName());
        return propertyService.getPropertyById(propertyId, ownerId);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createProperty(
            @RequestBody PropertyDto dto,
            Authentication authentication
    ) {
        Long ownerId = Long.valueOf(authentication.getName());
        return propertyService.createProperty(ownerId, dto);
    }

    @PutMapping("/{propertyId}")
    public ResponseEntity<ApiResponse> updateProperty(
            @PathVariable Long propertyId,
            @RequestBody PropertyDto dto,
            Authentication authentication
    ) {
        Long ownerId = Long.valueOf(authentication.getName());
        return propertyService.updateProperty(propertyId, ownerId, dto);
    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<ApiResponse> deleteProperty(
            @PathVariable Long propertyId,
            Authentication authentication
    ) {
        Long ownerId = Long.valueOf(authentication.getName());
        return propertyService.deleteProperty(propertyId, ownerId);
    }
}

