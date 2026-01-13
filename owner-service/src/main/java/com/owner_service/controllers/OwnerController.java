package com.owner_service.controllers;

import com.owner_service.dtos.OwnerDto;
import com.owner_service.exceptions.ApiResponse;
import com.owner_service.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
@PreAuthorize("hasRole('OWNER')")
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse> getMyOwnerProfile(
            Authentication authentication
    ) {
        Long userId = Long.valueOf(authentication.getName());
        return ownerService.getOwnerDetailsById(userId);
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse> updateOwnerProfile(
            @RequestBody OwnerDto ownerDto,
            Authentication authentication
    ) {
        Long userId = Long.valueOf(authentication.getName());
        return ownerService.updateOwner(userId, ownerDto);
    }
}

