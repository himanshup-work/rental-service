package com.owner_service.controllers;

import com.owner_service.dtos.OwnerDto;
import com.owner_service.exceptions.ApiResponse;
import com.owner_service.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for owner profile management.
 */
@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
@PreAuthorize("hasRole('OWNER')")
public class OwnerController {

    private final OwnerService ownerService;

    /**
     * Retrieves the profile of the currently authenticated owner.
     * @param authentication the security context authentication
     * @return a ResponseEntity containing the owner profile
     */
    @GetMapping("/me")
    public ResponseEntity<ApiResponse> getMyOwnerProfile(
            Authentication authentication
    ) {
        Long userId = Long.valueOf(authentication.getName());
        return ownerService.getOwnerByUserId(userId);
    }

    /**
     * Updates the profile of the currently authenticated owner.
     * @param ownerDto the updated profile details
     * @param authentication the security context authentication
     * @return a ResponseEntity containing the updated profile
     */
    @PutMapping("/me")
    public ResponseEntity<ApiResponse> updateOwnerProfile(
            @RequestBody OwnerDto ownerDto,
            Authentication authentication
    ) {
        Long userId = Long.valueOf(authentication.getName());
        return ownerService.updateOwner(userId, ownerDto);
    }
}

