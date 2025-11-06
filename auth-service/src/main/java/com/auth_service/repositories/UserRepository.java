package com.auth_service.repositories;

import com.auth_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, String> {

    Optional<User> findByEmail(String username);
    // TODO
}
