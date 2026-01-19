package com.tenant_service.repositories;

import com.tenant_service.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository
    extends JpaRepository<Tenant, Long> {

}
