package com.Regex.LearningManagementSystem.repositories;

import com.Regex.LearningManagementSystem.entities.Role;
import com.Regex.LearningManagementSystem.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.List;
import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);

    Optional<Users> findByEmail(String email);

    List<Users> findByRole(Role role);

    boolean existsByUsername(String username);

    Optional<Users> findByUsername(String username);
}
