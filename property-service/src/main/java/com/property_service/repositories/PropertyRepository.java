package com.property_service.repositories;

import com.property_service.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for {@link com.property_service.entities.Property} entity.
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    /**
     * Find all properties by owner ID.
     * @param ownerId the ID of the owner
     * @return list of properties owned by the specified owner
     */
    List<Property> findAllByOwnerId(Long ownerId);
}
