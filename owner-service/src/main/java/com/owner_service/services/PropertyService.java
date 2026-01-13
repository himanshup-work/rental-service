package com.owner_service.services;

import com.owner_service.dtos.PropertyDto;
import com.owner_service.exceptions.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Service class for property-related operations.
 * NOTE: Local persistence has been removed. Integration with external property-service is required.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PropertyService {

    /**
     * Retrieves all properties owned by a specific owner (via userId).
     * @param userId the user ID of the owner
     * @return a ResponseEntity containing the list of properties
     */
    public ResponseEntity<ApiResponse> getPropertiesByOwner(Long userId) {
        log.debug("Fetching properties for owner userId: {} from property-service", userId);
        // TODO: Implement Feign client or RestTemplate call to property-service
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("This feature is currently being migrated to property-service.")
                        .data(new ArrayList<>())
                        .statusCode(HttpStatus.OK)
                        .build()
        );
    }

    /**
     * Retrieves a specific property by ID and verifies ownership.
     * @param propertyId the ID of the property
     * @param userId the user ID of the owner
     * @return a ResponseEntity containing the property details
     */
    public ResponseEntity<ApiResponse> getPropertyById(Long propertyId, Long userId) {
        log.debug("Fetching property: {} for owner userId: {} from property-service", propertyId, userId);
        // TODO: Implement Feign client or RestTemplate call to property-service
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("This feature is currently being migrated to property-service.")
                        .statusCode(HttpStatus.OK)
                        .build()
        );
    }

    /**
     * Creates a new property for an owner.
     * @param userId the user ID of the owner
     * @param dto the property details
     * @return a ResponseEntity containing the created property
     */
    public ResponseEntity<ApiResponse> createProperty(Long userId, PropertyDto dto) {
        log.debug("Creating property for owner userId: {} in property-service", userId);
        // TODO: Implement Feign client or RestTemplate call to property-service
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message("This feature is currently being migrated to property-service.")
                        .statusCode(HttpStatus.SERVICE_UNAVAILABLE)
                        .build(),
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }

    /**
     * Updates an existing property and verifies ownership.
     * @param propertyId the ID of the property
     * @param userId the user ID of the owner
     * @param dto the updated property details
     * @return a ResponseEntity containing the updated property
     */
    public ResponseEntity<ApiResponse> updateProperty(Long propertyId, Long userId, PropertyDto dto) {
        log.debug("Updating property: {} for owner userId: {} in property-service", propertyId, userId);
        // TODO: Implement Feign client or RestTemplate call to property-service
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("This feature is currently being migrated to property-service.")
                        .statusCode(HttpStatus.OK)
                        .build()
        );
    }

    /**
     * Deletes a property and verifies ownership.
     * @param propertyId the ID of the property
     * @param userId the user ID of the owner
     * @return a ResponseEntity indicating success
     */
    public ResponseEntity<ApiResponse> deleteProperty(Long propertyId, Long userId) {
        log.debug("Deleting property: {} for owner userId: {} in property-service", propertyId, userId);
        // TODO: Implement Feign client or RestTemplate call to property-service
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("This feature is currently being migrated to property-service.")
                        .statusCode(HttpStatus.OK)
                        .build()
        );
    }
}
