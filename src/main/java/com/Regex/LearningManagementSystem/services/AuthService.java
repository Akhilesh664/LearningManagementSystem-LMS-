package com.Regex.LearningManagementSystem.services;

import com.Regex.LearningManagementSystem.entities.Role;

public interface AuthService {

    String login(String email, String password);

    String register(String name, String email, String password, Role role, String phoneNo, String qualification, Long batchId);

}
