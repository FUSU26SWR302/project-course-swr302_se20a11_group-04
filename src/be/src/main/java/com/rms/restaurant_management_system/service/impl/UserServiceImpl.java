package com.rms.restaurant_management_system.service.impl;

import com.rms.restaurant_management_system.dto.request.UpdateUserRequest;
import com.rms.restaurant_management_system.dto.response.UserResponse;
import com.rms.restaurant_management_system.entity.User;
import com.rms.restaurant_management_system.entity.Role;
import com.rms.restaurant_management_system.enums.RoleName;
import com.rms.restaurant_management_system.exception.BadRequestException;
import com.rms.restaurant_management_system.exception.ResourceNotFoundException;
import com.rms.restaurant_management_system.repository.RoleRepository;
import java.util.HashSet;
import com.rms.restaurant_management_system.repository.UserRepository;
import com.rms.restaurant_management_system.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {
        return toResponse(findById(id));
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        User user = findById(id);
        if (request.getFullName() != null) user.setFullName(request.getFullName());
        if (request.getPhone() != null) user.setPhone(request.getPhone());
        if (request.getAddress() != null) user.setAddress(request.getAddress());
        if (request.getAvatarUrl() != null) user.setAvatarUrl(request.getAvatarUrl());
        return toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse changeUserStatus(Long id, com.rms.restaurant_management_system.enums.UserStatus status) {
        User user = findById(id);
        user.setStatus(status);
        return toResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(findById(id));
    }

    @Override
    public UserResponse changeUserRole(Long id, String role) {
        User user = findById(id);
        try {
            RoleName roleName = RoleName.valueOf(role);
            Role newRole = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new BadRequestException("Role not found: " + role));
            user.getRoles().clear();
            user.getRoles().add(newRole);
            return toResponse(userRepository.save(user));
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid role: " + role);
        }
    }

    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .address(user.getAddress())
                .avatarUrl(user.getAvatarUrl())
                .status(user.getStatus())
                .roles(user.getRoles().stream().map(r -> r.getName().name()).collect(Collectors.toSet()))
                .createdAt(user.getCreatedAt())
                .build();
    }
}
