package com.Regex.LearningManagementSystem.services;

import com.Regex.LearningManagementSystem.dto.UsersRequestDto;
import com.Regex.LearningManagementSystem.dto.UsersResponseDto;
import com.Regex.LearningManagementSystem.entities.Role;

import java.util.List;

public interface UsersService {

    // Read operations
    UsersResponseDto getUserById(Long userId);
    UsersResponseDto getUserByEmail(String email);
    UsersResponseDto getUserByUsername(String username);
    List<UsersResponseDto> getAllUsers();
    List<UsersResponseDto> getUsersByRole(String role);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);


    // Update operations
    UsersResponseDto updateUser(Long userId, UsersRequestDto usersRequestDto);
    UsersResponseDto updateUserProfile(Long userId, UsersRequestDto usersRequestDto);
    void changeUserPassword(Long userId, String currentPassword, String newPassword);
    UsersResponseDto updateUserStatus(Long userId, boolean isActive);
    UsersResponseDto updateUserRole(Long userId, Role role);


    // Delete operations
    void deleteUser(Long userId);
    void deactivateUser(Long userId);


    // TODO: Implement me afterwords
//    // Authentication & Authorization
//    UsersResponseDto authenticateUser(String usernameOrEmail, String password);
//    void requestPasswordReset(String email);
//    void resetPassword(String token, String newPassword);
//    void verifyEmail(String verificationToken);
//
//    // Additional user management
//    List<UsersResponseDto> searchUsers(String query);
//    long countActiveUsers();
//    long countUsersByRole(String role);



}
