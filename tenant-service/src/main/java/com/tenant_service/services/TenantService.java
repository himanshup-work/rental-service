package com.tenant_service.services;

import com.tenant_service.dtos.ApiResponse;
import com.tenant_service.dtos.TenantDto;
import com.tenant_service.entities.Tenant;
import com.tenant_service.exceptions.ResourceNotFoundException;
import com.tenant_service.mappers.TenantMapper;
import com.tenant_service.repositories.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TenantService {
    private final TenantRepository tenantRepository;

    public ResponseEntity<ApiResponse> getAllTenants() {
        List<Tenant> tenants;
        try {
            tenants = tenantRepository.findAll();
        } catch (Exception ex) {
            throw new RuntimeException("Failed to fetch tenants: " + ex.getMessage());
        }
        // Implementation logic to retrieve all tenants
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Fetched all tenants successfully")
                .data(tenants)
                .statusCode(HttpStatus.OK)
                .build());
    }

    public ResponseEntity<ApiResponse> addTenant(TenantDto tenantDto) {
        Tenant tenant = TenantMapper.mapToEntity(tenantDto);
        Tenant savedTenant;
        try {
            savedTenant = tenantRepository.save(tenant);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to save tenant: " + ex.getMessage());
        }
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Tenant added successfully")
                .data(TenantMapper.mapToDto(savedTenant))
                .statusCode(HttpStatus.CREATED)
                .build());
    }

    public ResponseEntity<ApiResponse> updateTenant(
        TenantDto tenantDto, Long tenantId) {
        Tenant existingTenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant", "id", tenantId.toString()));
        existingTenant.setFullName(tenantDto.getFullName());
        existingTenant.setPhone(tenantDto.getPhone());
        Tenant updatedTenant;
        try {
            updatedTenant = tenantRepository.save(existingTenant);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to update tenant: " + ex.getMessage());
        }
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Tenant updated successfully")
                .data(TenantMapper.mapToDto(updatedTenant))
                .statusCode(HttpStatus.OK)
                .build());
    }

    public ResponseEntity<ApiResponse> deleteTenant(Long tenantId) {
        try {
            tenantRepository.deleteById(tenantId);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Tenant", "id", tenantId.toString());
        }
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Tenant deleted successfully")
                .statusCode(HttpStatus.OK)
                .build());
    }


}
