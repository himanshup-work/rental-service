package com.owner_service.services;

import com.owner_service.dtos.PropertyDto;
import com.owner_service.exceptions.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {
    public ResponseEntity<ApiResponse> getPropertiesByOwner(Long ownerId) {
        return null;
    }

    public ResponseEntity<ApiResponse> getPropertyById(Long propertyId, Long ownerId) {
        return null;
    }

    public ResponseEntity<ApiResponse> createProperty(Long ownerId, PropertyDto dto) {
        return null;
    }

    public ResponseEntity<ApiResponse> updateProperty(Long propertyId, Long ownerId, PropertyDto dto) {
        return null;
    }

    public ResponseEntity<ApiResponse> deleteProperty(Long propertyId, Long ownerId) {
        return null;
    }
}
