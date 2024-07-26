package org.yascode.role_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.role_management.entity.CustomField;

import java.util.Optional;

public interface CustomFieldRepository extends JpaRepository<CustomField, Long> {
    Optional<CustomField> findByField(String field);
}
