package com.Regex.LearningManagementSystem.services.Impl;

import com.Regex.LearningManagementSystem.entities.Users;
import com.Regex.LearningManagementSystem.entities.Batch;
import com.Regex.LearningManagementSystem.entities.Role;
import com.Regex.LearningManagementSystem.exceptions.ResourceNotFoundException;
import com.Regex.LearningManagementSystem.repositories.UsersRepository;
import com.Regex.LearningManagementSystem.repositories.BatchRepository;
import com.Regex.LearningManagementSystem.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsersRepository usersRepository;
    private final BatchRepository batchRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public String register(String name, String email, String password, Role role,
                         String phoneNo, String qualification, Long batchId) {
        
        if (usersRepository.existsByEmail(email)) {
            throw new ResourceNotFoundException("Email already registered");
        }

        // Only fetch batch if it's a student (students are assigned to batches)
        Batch batch = null;
        if (role == Role.STUDENT && batchId != null) {
            batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found with id: " + batchId));
        }

        Users user = new Users();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.valueOf(role.toUpperCase()));
        user.setPhoneNo(phoneNo);
        user.setQualification(qualification);
        user.setBatch(batch);  // Set the Batch object, not just the ID
        
        usersRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public String login(String email, String password) {

        Users user = usersRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Invalid Credentials"));

        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new ResourceNotFoundException("Invalid Credentials");
        }

        return "Login successful";
    }

}
