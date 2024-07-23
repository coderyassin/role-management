package org.yascode.role_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.role_management.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
