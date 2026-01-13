package com.owner_service.services;

import com.owner_service.dtos.OwnerDto;
import com.owner_service.entities.Owner;
import com.owner_service.exceptions.ApiResponse;
import com.owner_service.exceptions.ResourceNotFoundException;
import com.owner_service.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing owner profiles.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerService {

    private final OwnerRepository ownerRepository;

    /**
     * Retrieves owner details by their user ID.
     * @param userId the user ID of the owner
     * @return a ResponseEntity containing the owner details
     */
    public ResponseEntity<ApiResponse> getOwnerByUserId(Long userId) {
        log.debug("Fetching owner details for userId: {}", userId);
        Owner owner = ownerRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner", "userId", String.valueOf(userId)));

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Owner retrieved successfully.")
                        .data(mapToDto(owner))
                        .statusCode(HttpStatus.OK)
                        .build()
        );
    }

    /**
     * Retrieves owner details by their email address.
     * @param email the email address of the owner
     * @return a ResponseEntity containing the owner details
     */
    public ResponseEntity<ApiResponse> getOwnerByEmail(String email) {
        log.debug("Fetching owner details for email: {}", email);
        Owner owner = ownerRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Owner", "email", email));

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Owner retrieved successfully.")
                        .data(mapToDto(owner))
                        .statusCode(HttpStatus.OK)
                        .build()
        );
    }

    /**
     * Updates an owner profile.
     * @param userId the user ID of the owner
     * @param ownerDto the updated owner details
     * @return a ResponseEntity containing the updated owner profile
     */
    public ResponseEntity<ApiResponse> updateOwner(Long userId, OwnerDto ownerDto) {
        log.debug("Updating owner profile for userId: {}", userId);
        Owner owner = ownerRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner", "userId", String.valueOf(userId)));

        owner.setBusinessName(ownerDto.getBusinessName());
        owner.setPhoneNumber(ownerDto.getPhoneNumber());
        owner.setAddress(ownerDto.getAddress());
        // Email and UserId shouldn't be updated here usually for security reasons

        Owner updatedOwner = ownerRepository.save(owner);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("Owner profile updated successfully.")
                        .data(mapToDto(updatedOwner))
                        .statusCode(HttpStatus.OK)
                        .build()
        );
    }

    private OwnerDto mapToDto(Owner owner) {
        return OwnerDto.builder()
                .id(owner.getId())
                .userId(owner.getUserId())
                .email(owner.getEmail())
                .businessName(owner.getBusinessName())
                .phoneNumber(owner.getPhoneNumber())
                .address(owner.getAddress())
                .createdAt(owner.getCreatedAt())
                .updatedAt(owner.getUpdatedAt())
                .build();
    }
}
