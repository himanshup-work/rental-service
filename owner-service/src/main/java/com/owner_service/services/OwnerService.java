package com.owner_service.services;

import com.owner_service.dtos.OwnerDto;
import com.owner_service.dtos.PropertyDto;
import com.owner_service.entities.Owner;
import com.owner_service.exceptions.ApiResponse;
import com.owner_service.exceptions.ResourceNotFoundException;
import com.owner_service.repositories.OwnerRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public ResponseEntity<ApiResponse> getOwnerDetailsById(
            @NonNull Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new
                ResourceNotFoundException("Owner", "id", String.valueOf(ownerId)));

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message("Owner retrieved successfully.")
                        .data(owner)
                        .statusCode(HttpStatus.FOUND)
                        .build(),
                HttpStatus.FOUND
        );
    }

    public ResponseEntity<ApiResponse> getOwnerDetailsByEmail(
            @NonNull String email) {
        Owner owner = ownerRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Owner", "email", email));
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message("Owner retrieved successfully.")
                        .data(owner)
                        .statusCode(HttpStatus.FOUND)
                        .build(),
                HttpStatus.FOUND
        );
    }

    public ResponseEntity<ApiResponse> updateOwner(Long userId, OwnerDto ownerDto) {
        return null;
    }
}
