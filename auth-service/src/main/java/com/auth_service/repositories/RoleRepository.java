package com.auth_service.repositories;

import com.auth_service.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository
    extends JpaRepository<Role, Long> {
    // Todo
}
