package org.yascode.role_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.role_management.entity.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
