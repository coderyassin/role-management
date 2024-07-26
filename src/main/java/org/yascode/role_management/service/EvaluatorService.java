package org.yascode.role_management.service;

import org.yascode.role_management.dto.ValueUserDto;
import org.yascode.role_management.model.Evaluation;
import org.yascode.role_management.querybuilder.Query;

import java.util.List;

public interface EvaluatorService {
    Evaluation evaluate(Query query, List<ValueUserDto> valuesUser);
}
