package org.yascode.role_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.role_management.entity.User;
import org.yascode.role_management.entity.UserValue;

import java.util.List;

public interface UserValueRepository extends JpaRepository<UserValue, Long> {
    List<UserValue> findUserValueByUser(User user);
}
