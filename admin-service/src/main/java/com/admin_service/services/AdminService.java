package com.admin_service.services;

import com.admin_service.dtos.ApiResponse;
import com.admin_service.entities.ApprovalStatus;
import com.admin_service.entities.OwnerRequest;
import com.admin_service.exceptions.ResourceNotFoundException;
import com.admin_service.feign.AuthServiceClient;
import com.admin_service.mapper.OwnerRequestResponseMapper;
import com.admin_service.repositories.OwnerRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;

/**
 * Service class for handling admin-related business logic.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final AuthServiceClient authClient;
    private final OwnerRequestRepository ownerRequestRepository;

    /**
     * Retrieves all users from the auth service.
     *
     * @param role The role to filter by (optional).
     * @return A ResponseEntity containing the API response with user data.
     */
    public ResponseEntity<ApiResponse> getAllUsers(
            @RequestParam(required = false) String role) {
        log.info("Fetching all users with role: {}", role);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message("Users retrieved successfully")
                        .data(authClient.getAllUsers(role))
                        .statusCode(org.springframework.http.HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    /**
     * Retrieves a specific user by ID from the auth service.
     *
     * @param userId The ID of the user.
     * @return A ResponseEntity containing the user details.
     */
    public ResponseEntity<ApiResponse> getUserById(Long userId) {
        log.info("Fetching user details for userId: {}", userId);
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message("User retrieved successfully")
                        .data(authClient.getUserById(userId))
                        .statusCode(org.springframework.http.HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    /**
     * Retrieves all owner registration requests.
     *
     * @return A ResponseEntity containing the list of owner requests.
     */
    public ResponseEntity<ApiResponse> getAllOwnerRequests() {
        log.info("Fetching all owner registration requests");
        val ownerRequestResponses = ownerRequestRepository.findAll()
                .stream()
                .map(OwnerRequestResponseMapper::map)
                .toList();
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message("Owner requests retrieved successfully")
                        .data(ownerRequestResponses)
                        .statusCode(org.springframework.http.HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    /**
     * Retrieves a specific owner request by ID.
     *
     * @param requestId The ID of the owner request.
     * @return A ResponseEntity containing the owner request details.
     */
    public ResponseEntity<ApiResponse> getOwnerRequestById(Long requestId) {
        log.info("Fetching owner request with id: {}", requestId);
        val ownerRequestResponse = OwnerRequestResponseMapper.map(getRequest(requestId));
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message("Owner request retrieved successfully")
                        .data(ownerRequestResponse)
                        .statusCode(org.springframework.http.HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    /**
     * Approves an owner registration request and updates the user's role.
     *
     * @param requestId The ID of the request to approve.
     */
    public void approveOwnerRequest(Long requestId) {
        log.info("Approving owner request with id: {}", requestId);
        OwnerRequest request = getRequest(requestId);
        validatePending(request);
        request.setStatus(ApprovalStatus.APPROVED);
        request.setReviewedAt(Instant.now());
        ownerRequestRepository.save(request);
        authClient.updateUserRole(
                request.getUserId(),
                com.admin_service.entities.Role.OWNER.name()
        );
        log.info("Successfully approved owner request and updated role for userId: {}", request.getUserId());
    }

    /**
     * Rejects an owner registration request with a reason.
     *
     * @param requestId The ID of the request to reject.
     * @param reason    The reason for rejection.
     */
    public void rejectOwnerRequest(Long requestId, String reason) {
        log.info("Rejecting owner request with id: {} for reason: {}", requestId, reason);
        val request = getRequest(requestId);
        validatePending(request);
        request.setStatus(ApprovalStatus.REJECTED);
        request.setReviewedAt(Instant.now());
        request.setRemarks(reason);
        ownerRequestRepository.save(request);
        log.info("Successfully rejected owner request with id: {}", requestId);
    }

    private OwnerRequest getRequest(Long id) {
        return ownerRequestRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Owner request not found with id: {}", id);
                    return new ResourceNotFoundException("Owner request", "request id", String.valueOf(id));
                });
    }

    private void validatePending(OwnerRequest request) {
        if (request.getStatus() != ApprovalStatus.PENDING) {
            log.warn("Attempted to process already processed request with id: {}", request.getId());
            throw new IllegalStateException("Request already processed");
        }
    }
}
