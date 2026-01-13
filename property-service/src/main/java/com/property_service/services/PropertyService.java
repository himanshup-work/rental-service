package com.property_service.services;

import com.property_service.PropertyMapper;
import com.property_service.dtos.PropertyDto;
import com.property_service.entities.Property;
import com.property_service.exceptions.ApiResponse;
import com.property_service.exceptions.ResourceNotFoundException;
import com.property_service.repositories.PropertyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for property-related operations.
 * NOTE: Local persistence has been removed. Integration with external property-service is required.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PropertyService {

    private final PropertyRepository propertyRepository;


    public ResponseEntity<ApiResponse> getPropertiesByOwnerId(Long ownerId) {
        List<Property> properties;
        try {
            properties = propertyRepository.findAllByOwnerId(ownerId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Properties", "ownerId", ownerId.toString());
        }
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message("Properties retrieved successfully")
                        .data(properties) // Integration with external property-service needed here
                        .statusCode(org.springframework.http.HttpStatus.OK)
                        .build(),
                org.springframework.http.HttpStatus.OK
        );
    }

    public ResponseEntity<ApiResponse> getProperties() {
        List<Property> properties;
        try {
            properties = propertyRepository.findAll();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Properties", "all", "all");
        }
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message("All properties retrieved successfully")
                        .data(properties) // Integration with external property-service needed here
                        .statusCode(org.springframework.http.HttpStatus.OK)
                        .build(),
                org.springframework.http.HttpStatus.OK
        );
    }

    public ResponseEntity<ApiResponse> getPropertyById(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property", "id", propertyId.toString()));
        PropertyDto propertyDto = PropertyMapper.mapToDto(property);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message("Property retrieved successfully")
                        .data(propertyDto) // Integration with external property-service needed here
                        .statusCode(org.springframework.http.HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    public ResponseEntity<ApiResponse> createProperty(PropertyDto propertyDto) {
        Property property = PropertyMapper.mapToEntity(propertyDto);
        propertyRepository.save(property);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message("Property created successfully")
                        .statusCode(org.springframework.http.HttpStatus.CREATED)
                        .build(),
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<ApiResponse> updateProperty(Long propertyId, PropertyDto propertyDto) {
        Property property = PropertyMapper.mapToEntity(propertyDto);
        property.setId(propertyId);
        propertyRepository.save(property);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message("Property updated successfully")
                        .statusCode(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    public ResponseEntity<ApiResponse> deleteProperty(Long propertyId) {
        try {
            propertyRepository.deleteById(propertyId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Property", "id", propertyId.toString());
        }
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message("Property deleted successfully")
                        .statusCode(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }
}
