package com.admin_service.feign;

import com.admin_service.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-service")
public interface AuthServiceClient {

    @GetMapping("/auth/getAllUsers")
    UserDto getAllUsers(@RequestParam(required = false) String role);

    @GetMapping("/auth/getUserById")
    UserDto getUserById(Long userId);

    @GetMapping("/auth/getUserByEmail")
    UserDto getUserByEmail(String email);

    @PutMapping("/auth/updateUserRole")
    void updateUserRole(Long userId, String owner);
}
