package com.Regex.LearningManagementSystem.services.Impl;

import com.Regex.LearningManagementSystem.dto.UsersRequestDto;
import com.Regex.LearningManagementSystem.dto.UsersResponseDto;
import com.Regex.LearningManagementSystem.entities.Batch;
import com.Regex.LearningManagementSystem.entities.Role;
import com.Regex.LearningManagementSystem.entities.Users;
import com.Regex.LearningManagementSystem.exceptions.ResourceNotFoundException;
import com.Regex.LearningManagementSystem.mappers.UsersMapper;
import com.Regex.LearningManagementSystem.repositories.BatchRepository;
import com.Regex.LearningManagementSystem.repositories.UsersRepository;
import com.Regex.LearningManagementSystem.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final BatchRepository batchRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UsersResponseDto getUserById(Long userId) {
        log.info("Fetching user with ID: {}", userId);
        try {
            Users user = usersRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
            log.debug("User found: {}", user.getEmail());
            return usersMapper.toDto(user);
        } catch (ResourceNotFoundException ex) {
            log.error("Error fetching user with ID {}: {}", userId, ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Unexpected error fetching user ID {}: {}", userId, ex.getMessage(), ex);
            throw new RuntimeException("Error occurred while fetching user by ID");
        }
    }


    @Override
    public UsersResponseDto getUserByEmail(String email) {
        log.info("Fetching user with email: {}", email);
        try {
            return usersRepository.findByEmail(email)
                    .map(usersMapper::toDto)
                    .orElseThrow(() -> {
                        log.warn("User not found with email: {}", email);
                        return new ResourceNotFoundException("User not found with email: " + email);
                    });
        } catch (Exception ex) {
            log.error("Error fetching user by email {}: {}", email, ex.getMessage(), ex);
            throw new RuntimeException("Error occurred while fetching user by email");
        }
    }


    @Override
    public UsersResponseDto getUserByUsername(String username) {
        log.info("Fetching user with username: {}", username);
        try {
            return usersRepository.findByUsername(username)
                    .map(usersMapper::toDto)
                    .orElseThrow(() -> {
                        log.warn("User not found with username: {}", username);
                        return new ResourceNotFoundException("User not found with username: " + username);
                    });
        } catch (Exception ex) {
            log.error("Error fetching user by username {}: {}", username, ex.getMessage(), ex);
            throw new RuntimeException("Error occurred while fetching user by username");
        }
    }


    @Override
    public List<UsersResponseDto> getAllUsers() {
        log.info("Fetching all users");
        try {
            List<Users> users = usersRepository.findAll();
            log.debug("Total users found: {}", users.size());
            return users.stream()
                    .map(usersMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error fetching all users: {}", ex.getMessage(), ex);
            throw new RuntimeException("Error occurred while fetching all users");
        }
    }


    @Override
    public List<UsersResponseDto> getUsersByRole(String role) {
        log.info("Fetching users with role: {}", role);
        try {
            List<Users> users = usersRepository.findByRole(Role.valueOf(role.toUpperCase()));
            return users.stream()
                    .map(usersMapper::toDto)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException ex) {
            log.error("Invalid role provided: {}", role);
            throw new RuntimeException("Invalid role: " + role);
        } catch (Exception ex) {
            log.error("Error fetching users by role {}: {}", role, ex.getMessage(), ex);
            throw new RuntimeException("Error occurred while fetching users by role");
        }
    }


    @Override
    public boolean existsByEmail(String email) {
        boolean exists = usersRepository.existsByEmail(email);
        log.debug("Email {} exists: {}", email, exists);
        return exists;
    }


    @Override
    public boolean existsByUsername(String username) {
        boolean exists = usersRepository.existsByUsername(username);
        log.debug("Username {} exists: {}", username, exists);
        return exists;
    }


//===============================================================================


    // [ADMIN, MANAGEMENT controls it] for updating user
    @Override
    public UsersResponseDto updateUser(Long userId, UsersRequestDto usersRequestDto) {
        log.info("Updating user with ID: {}", userId);
        try {
            Users user = usersRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
            user.setName(usersRequestDto.getName());
            user.setEmail(usersRequestDto.getEmail());
            user.setRole(usersRequestDto.getRole());
            user.setPhoneNo(usersRequestDto.getPhoneNo());
            user.setQualification(usersRequestDto.getQualification());

            if (usersRequestDto.getBatchId() != null) {
                Batch batch = batchRepository.findById(usersRequestDto.getBatchId())
                        .orElseThrow(() -> new ResourceNotFoundException("Batch not found with ID: " + usersRequestDto.getBatchId()));
                user.setBatch(batch);
            } else {
                user.setBatch(null);  // or keep existing batch if that's the desired behavior
            }

            Users updatedUser = usersRepository.save(user);
            log.debug("User updated successfully: {}", updatedUser.getEmail());
            return usersMapper.toDto(updatedUser);

        } catch (ResourceNotFoundException ex) {
            log.error("Error updating user: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Unexpected error while updating user ID {}: {}", userId, ex.getMessage(), ex);
            throw new RuntimeException("An error occurred while updating user");
        }

    }


    // [USER controls own profile] for updating User's profile
    @Override
    public UsersResponseDto updateUserProfile(Long userId, UsersRequestDto usersRequestDto) {
        log.info("Updating user profile for user ID: {}", userId);
        try {
            Users user = usersRepository.findById(userId)
                    .orElseThrow(() -> {
                        log.warn("User not found with ID: {}", userId);
                        return new ResourceNotFoundException("User not found with ID: " + userId);
                    });
            user.setName(usersRequestDto.getName());
            user.setEmail(usersRequestDto.getEmail());
            user.setPhoneNo(usersRequestDto.getPhoneNo());
            user.setQualification(usersRequestDto.getQualification());

            Users savedUser = usersRepository.save(user);
            log.debug("User profile updated successfully: {}", savedUser.getEmail());
            return usersMapper.toDto(savedUser);

        }  catch (Exception ex) {
            log.error("Error updating user profile: {}", ex.getMessage(), ex);
            throw new RuntimeException("Error occurred while updating user profile");
        }

    }

    @Override
    public void changeUserPassword(Long userId, String currentPassword, String newPassword) {
        log.info("Changing password for user ID: {}", userId);
        try {
            Users user = usersRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

            if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
                log.warn("Invalid current password for user ID: {}", userId);
                throw new ResourceNotFoundException("Invalid Credentials");
            }

            user.setPassword(passwordEncoder.encode(newPassword));
            usersRepository.save(user);
            log.info("Password changed successfully for user ID: {}", userId);
        } catch (ResourceNotFoundException ex) {
            log.error("Error changing password: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Error changing password for user ID {}: {}", userId, ex.getMessage(), ex);
            throw new RuntimeException("Error occurred while changing user password");
        }
    }

    // make UserUpdateActiveStatus active or inactive

    @Override
    public UsersResponseDto updateUserStatus(Long userId, boolean isActive) {
        log.info("Updating status for user ID: {} to {}", userId, isActive);
        try {
            Users user = usersRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
            user.setActive(isActive);
            Users updated = usersRepository.save(user);
            log.info("User status updated successfully for user ID: {}", userId);
            return usersMapper.toDto(updated);
        } catch (Exception ex) {
            log.error("Error updating user status for ID {}: {}", userId, ex.getMessage(), ex);
            throw new RuntimeException("Error occurred while updating user status");
        }
    }


    @Override
    public UsersResponseDto updateUserRole(Long userId, Role role) {
        log.info("Updating role for user ID: {} to {}", userId, role);
        try {
            Users user = usersRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
            user.setRole(role);
            return usersMapper.toDto(usersRepository.save(user));
        } catch (Exception ex) {
            log.error("Error updating user role for ID {}: {}", userId, ex.getMessage(), ex);
            throw new RuntimeException("Error occurred while updating user role");
        }
    }


//===============================================================================


    @Override
    public void deleteUser(Long userId) {
        log.info("Deleting user with ID: {}", userId);
        try {
            Users user = usersRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
            usersRepository.delete(user);
            log.info("User deleted successfully: {}", userId);
        } catch (Exception ex) {
            log.error("Error deleting user ID {}: {}", userId, ex.getMessage(), ex);
            throw new RuntimeException("Error occurred while deleting user");
        }
    }


    @Override
    public void deactivateUser(Long userId) {
        log.info("Deactivating user with ID: {}", userId);
        try {
            Users user = usersRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
            user.setActive(false);
            usersRepository.save(user);
            log.info("User deactivated successfully: {}", userId);
        } catch (Exception ex) {
            log.error("Error deactivating user ID {}: {}", userId, ex.getMessage(), ex);
            throw new RuntimeException("Error occurred while deactivating user");
        }
    }

}
