package com.tenant_service.controllers;

import com.tenant_service.dtos.ApiResponse;
import com.tenant_service.dtos.TenantDto;
import com.tenant_service.services.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tenants")
@RequiredArgsConstructor
public class TenantController {
    private final TenantService tenantService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllTenants() {
        return tenantService.getAllTenants();
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> addTenant(
            @RequestBody TenantDto tenantDto) {
        return tenantService.addTenant(tenantDto);
    }

    @PutMapping("/edit/{tenantId}")
    public ResponseEntity<ApiResponse> editTenant(
            @RequestBody TenantDto tenantDto,
            @PathVariable("tenantId") Long tenantId) {
        return tenantService.updateTenant(tenantDto, tenantId);
    }

    @DeleteMapping("/delete/{tenantId}")
    public ResponseEntity<ApiResponse> deleteTenant(
            @PathVariable Long tenantId) {
        return tenantService.deleteTenant(tenantId);
    }

}
