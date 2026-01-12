package com.admin_service.controllers;

import com.admin_service.dtos.ApiResponse;
import com.admin_service.dtos.RejectOwnerRequest;
import com.admin_service.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for admin-related operations.
 */
@RestController
@RequestMapping("/admin")
//@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
// =========================
    // USER MANAGEMENT
    // =========================

    /**
     * Retrieves all users, optionally filtered by role.
     *
     * @param role The role to filter by (optional).
     * @return A ResponseEntity containing the list of users.
     */
    @GetMapping("/users")
    public ResponseEntity<ApiResponse> getAllUsers(
            @RequestParam(required = false) String role) {
        return adminService.getAllUsers(role);
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param userId The ID of the user.
     * @return A ResponseEntity containing the user details.
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse> getUserByUserId(
            @PathVariable Long userId ) {
        return adminService.getUserById(userId);
    }

    // =========================
    // OWNER REQUEST MANAGEMENT
    // =========================

    /**
     * Retrieves all owner registration requests.
     *
     * @return A ResponseEntity containing the list of owner requests.
     */
    @GetMapping("/owner-requests")
    public ResponseEntity<ApiResponse> getAllOwnerRequests() {
        return adminService.getAllOwnerRequests();
    }

    /**
     * Retrieves an owner registration request by its ID.
     *
     * @param requestId The ID of the owner request.
     * @return A ResponseEntity containing the owner request details.
     */
    @GetMapping("/owner-requests/{requestId}")
    public ResponseEntity<ApiResponse> getOwnerRequestById(
            @PathVariable Long requestId
    ) {
        return adminService.getOwnerRequestById(requestId);
    }

    /**
     * Approves an owner registration request.
     *
     * @param requestId The ID of the request to approve.
     * @return A ResponseEntity indicating success.
     */
    @PutMapping("/owner-requests/{requestId}/approve")
    public ResponseEntity<ApiResponse> approveOwnerRequest(
            @PathVariable Long requestId) {
        adminService.approveOwnerRequest(requestId);
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Owner request approved successfully")
                .statusCode(HttpStatus.OK)
                .build());
    }

    /**
     * Rejects an owner registration request.
     *
     * @param requestId The ID of the request to reject.
     * @param request   The rejection details (reason).
     * @return A ResponseEntity indicating success.
     */
    @PutMapping("/owner-requests/{requestId}/reject")
    public ResponseEntity<ApiResponse> rejectOwnerRequest(
            @PathVariable Long requestId,
            @RequestBody RejectOwnerRequest request) {
        adminService.rejectOwnerRequest(requestId, request.getReason());
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Owner request rejected successfully")
                .statusCode(HttpStatus.OK)
                .build());
    }
}

