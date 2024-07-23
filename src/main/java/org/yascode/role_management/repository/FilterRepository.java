package org.yascode.role_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.role_management.entity.Filter;

public interface FilterRepository extends JpaRepository<Filter, Long> {
}
