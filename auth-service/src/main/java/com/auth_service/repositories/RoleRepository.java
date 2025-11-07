package com.auth_service.repositories;

import com.auth_service.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository
    extends JpaRepository<Role, Long> {
    // Todo
    Optional<Role> findByName(String name);
}
