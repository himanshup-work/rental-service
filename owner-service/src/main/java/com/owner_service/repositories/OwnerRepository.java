package com.owner_service.repositories;

import com.owner_service.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for {@link Owner} entity.
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    
    /**
     * Finds an owner by their email address.
     * @param email the email address to search for
     * @return an Optional containing the owner if found, or empty otherwise
     */
    Optional<Owner> findByEmail(String email);

    /**
     * Finds an owner by their associated User ID.
     * @param userId the user ID to search for
     * @return an Optional containing the owner if found, or empty otherwise
     */
    Optional<Owner> findByUserId(Long userId);
}
