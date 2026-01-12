package com.admin_service.feign;

import com.admin_service.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "auth-service",
        url = "http://localhost:8081"
)
public interface AuthServiceClient {

    @GetMapping("/auth/getAllUsers")
    UserDto getAllUsers(@RequestParam(required = false) String role);

    @GetMapping("/auth/users/{id}")
    UserDto getUserById(@PathVariable("id") Long userId);

    @GetMapping("/auth/users/email/{email}")
    UserDto getUserByEmail(@PathVariable("email") String email);

    @PutMapping("/auth/users/{id}/role/{role}")
    void updateUserRole(
            @PathVariable("id") Long userId,
            @PathVariable("role") String owner
    );

}
