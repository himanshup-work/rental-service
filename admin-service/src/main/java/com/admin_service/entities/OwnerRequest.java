package com.admin_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Builder
@Data
@Table(name = "owner_requests")
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String businessName;

    @Column(nullable = false)
    private String documentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApprovalStatus status;

    @Column(nullable = false)
    private Instant requestedAt;

    private Instant reviewedAt;

    private Long reviewedBy; // admin userId

    private String remarks;
}

