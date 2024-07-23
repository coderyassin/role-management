package org.yascode.role_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.role_management.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}