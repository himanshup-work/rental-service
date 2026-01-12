package com.admin_service.repositories;

import com.admin_service.entities.ApprovalStatus;
import com.admin_service.entities.OwnerRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRequestRepository
        extends JpaRepository<OwnerRequest, Long> {

    List<OwnerRequest> findByStatus(ApprovalStatus status);

    boolean existsByUserIdAndStatus(Long userId, ApprovalStatus status);
}
