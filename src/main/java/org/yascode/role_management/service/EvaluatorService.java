package org.yascode.role_management.service;

import org.yascode.role_management.model.Evaluation;
import org.yascode.role_management.querybuilder.Query;

import java.util.Map;

public interface EvaluatorService {
    Evaluation evaluate(Query query, Map<String, Object> userMap);
}
