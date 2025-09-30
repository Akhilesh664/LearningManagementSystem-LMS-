package com.Regex.LearningManagementSystem.controllers;

import com.Regex.LearningManagementSystem.dto.UsersRequestDto;
import com.Regex.LearningManagementSystem.dto.UsersResponseDto;
import com.Regex.LearningManagementSystem.entities.Role;
import com.Regex.LearningManagementSystem.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponseDto> getUserById(@PathVariable Long id) {
        log.info("Retrieving user with ID: {}", id);
        UsersResponseDto response = usersService.getUserById(id);
        log.debug("User retrieved successfully with ID: {}", id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsersResponseDto> getUserByEmail(@PathVariable String email) {
        log.info("Retrieving user with EmailID: {}", email);
        UsersResponseDto response = usersService.getUserByEmail(email);
        log.debug("User retrieved successfully with EmailID: {}", email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UsersResponseDto> getUserByUsername(@PathVariable String username) {
        log.info("Fetching user by username: {}", username);
        UsersResponseDto user = usersService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UsersResponseDto>> getAllUsers() {
        log.info("Fetching all users");
        List<UsersResponseDto> users = usersService.getAllUsers();
        log.info("Total users fetched: {}", users.size());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UsersResponseDto>> getUsersByRole(@PathVariable String role) {
        log.info("Fetching users by role: {}", role);
        List<UsersResponseDto> users = usersService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> existsByEmail(String email) {
        log.info("Checking if email exists: {}", email);
        boolean exists = usersService.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/username/{username}")
    public ResponseEntity<Boolean> existsByUsername(String username) {
        log.info("Checking if username exists: {}", username);
        boolean exists = usersService.existsByUsername(username);
        return ResponseEntity.ok(exists);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersResponseDto> updateUser(@PathVariable Long id, @RequestBody UsersRequestDto usersRequestDto) {
        log.info("Updating user with ID: {}", id);
        UsersResponseDto updatedUser = usersService.updateUser(id, usersRequestDto);
        log.info("User updated successfully with ID: {}", id);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<UsersResponseDto> updateUserProfile(@PathVariable Long id, @RequestBody UsersRequestDto requestDto) {
        log.info("Updating user profile with ID: {}", id);
        UsersResponseDto updatedProfile = usersService.updateUserProfile(id, requestDto);
        return ResponseEntity.ok(updatedProfile);
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<String> changeUserPassword(@PathVariable Long id, @RequestParam String oldPassword, @RequestParam String newPassword) {
        log.info("Changing password for user with ID: {}", id);
        usersService.changeUserPassword(id, oldPassword, newPassword);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<UsersResponseDto> updateUserStatus(@PathVariable Long id, @RequestParam boolean active) {
        log.info("Updating status for user ID: {} to {}", id, active);
        UsersResponseDto response = usersService.updateUserStatus(id, active);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<UsersResponseDto> updateUserRole(@PathVariable Long id, @RequestParam Role role) {
        log.info("Updating role for user ID: {} to {}", id, role);
        UsersResponseDto response = usersService.updateUserRole(id, role);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        log.info("Deleting user with ID: {}", id);
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateUser(@PathVariable Long id) {
        log.info("Deactivating user with ID: {}", id);
        usersService.deactivateUser(id);
        return ResponseEntity.ok("User deactivated successfully");
    }

}

// [Important] NOTE: for returning responses codes
// ResponseEntity.ok(response) for 200 OK.
// ResponseEntity.status(HttpStatus.CREATED).body(response) for 201 Created.
// ResponseEntity.noContent().build() for 204 No Content.