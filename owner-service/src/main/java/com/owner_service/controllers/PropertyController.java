package com.owner_service.controllers;

import com.owner_service.dtos.PropertyDto;
import com.owner_service.exceptions.ApiResponse;
import com.owner_service.services.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing properties listed by an owner.
 */
@RestController
@RequestMapping("/owner/properties")
@RequiredArgsConstructor
@PreAuthorize("hasRole('OWNER')")
public class PropertyController {

    private final PropertyService propertyService;

    /**
     * Retrieves all properties owned by the currently authenticated owner.
     * @param authentication the security context authentication
     * @return a ResponseEntity containing the list of properties
     */
    @GetMapping
    public ResponseEntity<ApiResponse> getMyProperties(
            Authentication authentication
    ) {
        Long ownerId = Long.valueOf(authentication.getName());
        return propertyService.getPropertiesByOwner(ownerId);
    }

    /**
     * Retrieves a specific property by ID.
     * @param propertyId the ID of the property
     * @param authentication the security context authentication
     * @return a ResponseEntity containing the property details
     */
    @GetMapping("/{propertyId}")
    public ResponseEntity<ApiResponse> getProperty(
            @PathVariable Long propertyId,
            Authentication authentication
    ) {
        Long ownerId = Long.valueOf(authentication.getName());
        return propertyService.getPropertyById(propertyId, ownerId);
    }

    /**
     * Creates a new property listing.
     * @param dto the property details
     * @param authentication the security context authentication
     * @return a ResponseEntity containing the created property
     */
    @PostMapping
    public ResponseEntity<ApiResponse> createProperty(
            @RequestBody PropertyDto dto,
            Authentication authentication
    ) {
        Long ownerId = Long.valueOf(authentication.getName());
        return propertyService.createProperty(ownerId, dto);
    }

    /**
     * Updates an existing property listing.
     * @param propertyId the ID of the property to update
     * @param dto the updated property details
     * @param authentication the security context authentication
     * @return a ResponseEntity containing the updated property
     */
    @PutMapping("/{propertyId}")
    public ResponseEntity<ApiResponse> updateProperty(
            @PathVariable Long propertyId,
            @RequestBody PropertyDto dto,
            Authentication authentication
    ) {
        Long ownerId = Long.valueOf(authentication.getName());
        return propertyService.updateProperty(propertyId, ownerId, dto);
    }

    /**
     * Deletes a property listing.
     * @param propertyId the ID of the property to delete
     * @param authentication the security context authentication
     * @return a ResponseEntity indicating success
     */
    @DeleteMapping("/{propertyId}")
    public ResponseEntity<ApiResponse> deleteProperty(
            @PathVariable Long propertyId,
            Authentication authentication
    ) {
        Long ownerId = Long.valueOf(authentication.getName());
        return propertyService.deleteProperty(propertyId, ownerId);
    }
}

