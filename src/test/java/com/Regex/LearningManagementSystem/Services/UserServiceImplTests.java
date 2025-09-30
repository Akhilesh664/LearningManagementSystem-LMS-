package com.Regex.LearningManagementSystem.Services;

import com.Regex.LearningManagementSystem.dto.UsersRequestDto;
import com.Regex.LearningManagementSystem.dto.UsersResponseDto;
import com.Regex.LearningManagementSystem.entities.Batch;
import com.Regex.LearningManagementSystem.entities.Role;
import com.Regex.LearningManagementSystem.entities.Users;
import com.Regex.LearningManagementSystem.exceptions.ResourceNotFoundException;
import com.Regex.LearningManagementSystem.mappers.UsersMapper;
import com.Regex.LearningManagementSystem.repositories.BatchRepository;
import com.Regex.LearningManagementSystem.repositories.UsersRepository;
import com.Regex.LearningManagementSystem.services.Impl.UsersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTests {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private UsersMapper usersMapper;

    @Mock
    private BatchRepository batchRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsersServiceImpl usersService;

    private Users user;
    private UsersResponseDto userResponseDto;
    private Batch batch;

    @BeforeEach
    void setUp() {
        batch = new Batch();
        batch.setId(1L);
        batch.setName("Spring Boot 2024");

        user = new Users();
        user.setId(1L);
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setRole(Role.STUDENT);
        user.setPhoneNo("1234567890");
        user.setQualification("B.Tech");
        user.setBatch(batch);
        user.setActive(true);

        userResponseDto = new UsersResponseDto();
        userResponseDto.setId(1L);
        userResponseDto.setName("Test User");
        userResponseDto.setEmail("test@example.com");
        userResponseDto.setRole(Role.STUDENT);
        userResponseDto.setPhoneNo("1234567890");
        userResponseDto.setQualification("B.Tech");
        userResponseDto.setBatchId(1L);
        userResponseDto.setActive(true);

        UsersRequestDto userRequestDto = new UsersRequestDto();
        userRequestDto.setName("Test User");
        userRequestDto.setEmail("test@example.com");
        userRequestDto.setRole(Role.STUDENT);
        userRequestDto.setPhoneNo("1234567890");
        userRequestDto.setQualification("B.Tech");
        userRequestDto.setBatchId(1L);
    }

    @Test
    void getUserById_ShouldReturnUser_WhenUserExists() {
        // Arrange
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));
        when(usersMapper.toDto(user)).thenReturn(userResponseDto);

        // Act
        UsersResponseDto result = usersService.getUserById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(userResponseDto.getId(), result.getId());
        assertEquals(userResponseDto.getName(), result.getName());
        verify(usersRepository, times(1)).findById(1L);
    }

    @Test
    void getUserById_ShouldThrowException_WhenUserNotFound() {
        // Arrange
        when(usersRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> usersService.getUserById(1L));
        verify(usersRepository, times(1)).findById(1L);
    }

    @Test
    void updateUser_ShouldUpdateUser_WhenUserExists() {
        // Arrange
        UsersRequestDto updateRequest = new UsersRequestDto();
        updateRequest.setName("Updated Name");
        updateRequest.setEmail("updated@example.com");
        updateRequest.setRole(Role.TEACHER);
        updateRequest.setBatchId(1L);

        Users updatedUser = new Users();
        updatedUser.setId(1L);
        updatedUser.setName("Updated Name");
        updatedUser.setEmail("updated@example.com");
        updatedUser.setRole(Role.TEACHER);
        updatedUser.setBatch(batch);

        UsersResponseDto updatedResponse = new UsersResponseDto();
        updatedResponse.setId(1L);
        updatedResponse.setName("Updated Name");
        updatedResponse.setEmail("updated@example.com");
        updatedResponse.setRole(Role.TEACHER);
        updatedResponse.setBatchId(1L);

        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));
        when(batchRepository.findById(1L)).thenReturn(Optional.of(batch));
        when(usersRepository.save(any(Users.class))).thenReturn(updatedUser);
        when(usersMapper.toDto(any(Users.class))).thenReturn(updatedResponse);

        // Act
        UsersResponseDto result = usersService.updateUser(1L, updateRequest);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        assertEquals(Role.TEACHER, result.getRole());
        verify(usersRepository, times(1)).save(any(Users.class));
    }

    @Test
    void deleteUser_ShouldDeleteUser_WhenUserExists() {
        // Arrange
        when(usersRepository.existsById(1L)).thenReturn(true);

        // Act
        usersService.deleteUser(1L);

        // Assert
        verify(usersRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUser_ShouldThrowException_WhenUserNotExists() {
        // Arrange
        when(usersRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> usersService.deleteUser(1L));
        verify(usersRepository, never()).deleteById(anyLong());
    }

    @Test
    void changeUserPassword_ShouldUpdatePassword_WhenCurrentPasswordIsCorrect() {
        // Arrange
        String currentPassword = "oldPassword";
        String newPassword = "newPassword";
        String encodedNewPassword = "encodedNewPassword";

        user.setPassword("encodedOldPassword");
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(currentPassword, user.getPassword())).thenReturn(true);
        when(passwordEncoder.encode(newPassword)).thenReturn(encodedNewPassword);

        // Act
        usersService.changeUserPassword(1L, currentPassword, newPassword);

        // Assert
        assertEquals(encodedNewPassword, user.getPassword());
        verify(usersRepository, times(1)).save(user);
    }

    @Test
    void getUsersByRole_ShouldReturnUsers_WhenRoleExists() {
        // Arrange
        List<Users> users = Collections.singletonList(user);
        when(usersRepository.findByRole(Role.STUDENT)).thenReturn(users);
        when(usersMapper.toDto(any(Users.class))).thenReturn(userResponseDto);

        // Act
        List<UsersResponseDto> result = usersService.getUsersByRole("STUDENT");

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(usersRepository, times(1)).findByRole(Role.STUDENT);
    }

    @Test
    void deactivateUser_ShouldDeactivateUser_WhenUserExists() {
        // Arrange
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));
        when(usersRepository.save(any(Users.class))).thenReturn(user);

        // Act
        usersService.deactivateUser(1L);

        // Assert
        assertFalse(user.isActive());
        verify(usersRepository, times(1)).save(user);
    }

    @Test
    void existsByEmail_ShouldReturnTrue_WhenEmailExists() {
        // Arrange
        when(usersRepository.existsByEmail("test@example.com")).thenReturn(true);

        // Act
        boolean exists = usersService.existsByEmail("test@example.com");

        // Assert
        assertTrue(exists);
        verify(usersRepository, times(1)).existsByEmail("test@example.com");
    }

    @Test
    void updateUserRole_ShouldUpdateRole_WhenUserExists() {
        // Arrange
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));
        when(usersRepository.save(any(Users.class))).thenReturn(user);
        when(usersMapper.toDto(any(Users.class))).thenReturn(userResponseDto);

        // Act
        UsersResponseDto result = usersService.updateUserRole(1L, Role.ADMIN);

        // Assert
        assertNotNull(result);
        verify(usersRepository, times(1)).save(user);
        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    void updateUserProfile_ShouldUpdateProfile_WhenUserExists() {
        // Arrange
        UsersRequestDto profileUpdate = new UsersRequestDto();
        profileUpdate.setName("New Name");
        profileUpdate.setEmail("new@example.com");
        profileUpdate.setPhoneNo("9876543210");
        profileUpdate.setQualification("M.Tech");

        Users updatedUser = new Users();
        updatedUser.setId(1L);
        updatedUser.setName("New Name");
        updatedUser.setEmail("new@example.com");
        updatedUser.setPhoneNo("9876543210");
        updatedUser.setQualification("M.Tech");

        UsersResponseDto updatedResponse = new UsersResponseDto();
        updatedResponse.setId(1L);
        updatedResponse.setName("New Name");
        updatedResponse.setEmail("new@example.com");
        updatedResponse.setPhoneNo("9876543210");
        updatedResponse.setQualification("M.Tech");

        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));
        when(usersRepository.save(any(Users.class))).thenReturn(updatedUser);
        when(usersMapper.toDto(any(Users.class))).thenReturn(updatedResponse);

        // Act
        UsersResponseDto result = usersService.updateUserProfile(1L, profileUpdate);

        // Assert
        assertNotNull(result);
        assertEquals("New Name", result.getName());
        assertEquals("new@example.com", result.getEmail());
        verify(usersRepository, times(1)).save(any(Users.class));
    }
}
