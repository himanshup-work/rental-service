package com.property_service.controllers;

import com.property_service.dtos.PropertyDto;
import com.property_service.exceptions.ApiResponse;
import com.property_service.services.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing properties listed by an owner.
 */
@RestController
@RequestMapping("/property")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("/owner/properties")
    public ResponseEntity<ApiResponse> getPropertiesByOwnerId(
            Authentication authentication){
        Long ownerId = Long.valueOf(authentication.getName());
        return propertyService.getPropertiesByOwnerId(ownerId);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProperties(){
        return propertyService.getProperties();
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<ApiResponse> getPropertyById(
            @PathVariable("propertyId") Long propertyId){
        return propertyService.getPropertyById(propertyId);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProperty(
            @RequestBody PropertyDto propertyDto) {
        return propertyService.createProperty(propertyDto);
    }

    @PutMapping("/update/{propertyId}")
    public ResponseEntity<ApiResponse> updateProperty(
            @PathVariable("propertyId") Long propertyId,
            @RequestBody PropertyDto propertyDto){
        return propertyService.updateProperty(propertyId, propertyDto);
    }

    @DeleteMapping("/delete/{propertyId}")
    public ResponseEntity<ApiResponse> deleteProperty(
            @PathVariable("propertyId") Long propertyId){
        return propertyService.deleteProperty(propertyId);
    }

}

