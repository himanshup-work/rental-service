package com.admin_service.mapper;

import com.admin_service.dtos.OwnerRequestResponse;
import com.admin_service.entities.OwnerRequest;

public class OwnerRequestResponseMapper {

    public static OwnerRequestResponse map(OwnerRequest request) {
        return new OwnerRequestResponse(
                request.getId(),
                request.getUserId(),
                request.getBusinessName(),
                request.getDocumentId(),
                request.getStatus().name(),
                request.getRequestedAt()
        );
    }
}

