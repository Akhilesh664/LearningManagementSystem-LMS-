package com.Regex.LearningManagementSystem.controllers;

import com.Regex.LearningManagementSystem.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Regex.LearningManagementSystem.dto.Auth.LoginRequest;
import com.Regex.LearningManagementSystem.dto.Auth.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        log.info("Login attempt for email: {}", loginRequest.getEmail());
        String token = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        log.info("Login successful for email: {}", loginRequest.getEmail());
        return ResponseEntity.ok(token);
    }

    // ✅ Register Endpoint
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        log.info("Registering user with email: {}", registerRequest.getEmail());
        String result = authService.register(
                registerRequest.getName(),
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getRole(),  // If you are using String role
                registerRequest.getPhoneNo(),
                registerRequest.getQualification(),
                registerRequest.getBatchId()
        );
        log.info("User registered successfully: {}", registerRequest.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}

// [Important] NOTE: for returning responses codes
// ResponseEntity.ok(response); for 200 OK. [put / get]
// ResponseEntity.status(HttpStatus.CREATED).body(response); for 201 Created. [created]
// ResponseEntity.noContent().build(); for 204 No Content. [delete]