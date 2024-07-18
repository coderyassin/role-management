package org.yascode.role_management.service.impl;

import org.springframework.stereotype.Service;
import org.yascode.role_management.model.Evaluation;
import org.yascode.role_management.querybuilder.Query;
import org.yascode.role_management.querybuilder.QueryEvaluator;
import org.yascode.role_management.service.EvaluatorService;

import java.util.Map;

@Service
public class EvaluatorServiceImpl implements EvaluatorService {
    private final QueryEvaluator queryEvaluator;

    public EvaluatorServiceImpl(QueryEvaluator queryEvaluator) {
        this.queryEvaluator = queryEvaluator;
    }

    @Override
    public Evaluation evaluate(Query query, Map<String, Object> userMap) {
        return queryEvaluator.evaluate(query, userMap);
    }
}
