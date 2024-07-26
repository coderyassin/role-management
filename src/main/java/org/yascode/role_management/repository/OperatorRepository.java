package org.yascode.role_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yascode.role_management.entity.Operator;
import org.yascode.role_management.util.OperatorEnum;

import java.util.Optional;

public interface OperatorRepository extends JpaRepository<Operator, Long> {
    Optional<Operator> findByName(OperatorEnum operatorEnum);
}
