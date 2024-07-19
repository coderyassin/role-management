package org.yascode.role_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.role_management.entity.UserValue;

public interface UserValueRepository extends JpaRepository<UserValue, Long> {
}
