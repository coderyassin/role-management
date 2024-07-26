package org.yascode.role_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.role_management.entity.Option;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
