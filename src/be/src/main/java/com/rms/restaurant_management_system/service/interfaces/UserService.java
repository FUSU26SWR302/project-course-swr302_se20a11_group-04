package com.rms.restaurant_management_system.service.interfaces;

import com.rms.restaurant_management_system.dto.request.UpdateUserRequest;
import com.rms.restaurant_management_system.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    UserResponse updateUser(Long id, UpdateUserRequest request);
    UserResponse changeUserStatus(Long id, com.rms.restaurant_management_system.enums.UserStatus status);
    UserResponse changeUserRole(Long id, String role);
    void deleteUser(Long id);
}
