package com.tenant_service.mappers;

import com.tenant_service.dtos.TenantDto;
import com.tenant_service.entities.Tenant;

public class TenantMapper {
    public static Tenant mapToEntity(
        TenantDto tenantDto) {

        return Tenant.builder()
                .id(tenantDto.getId())
                .userId(tenantDto.getUserId())
                .fullName(tenantDto.getFullName())
                .phone(tenantDto.getPhone())
                .createdAt(tenantDto.getCreatedAt())
                .build();
    }

    public static TenantDto mapToDto(
        Tenant tenant) {

        return TenantDto.builder()
                .id(tenant.getId())
                .userId(tenant.getUserId())
                .fullName(tenant.getFullName())
                .phone(tenant.getPhone())
                .createdAt(tenant.getCreatedAt())
                .build();
    }
}
