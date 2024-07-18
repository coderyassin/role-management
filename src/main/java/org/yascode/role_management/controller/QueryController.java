package org.yascode.role_management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.role_management.controller.api.QueryApi;
import org.yascode.role_management.querybuilder.Query;
import org.yascode.role_management.service.EvaluatorService;

import java.util.Map;

@RestController
@RequestMapping(value = "/queries")
public class QueryController implements QueryApi {
    private final EvaluatorService evaluatorService;
    private final Map<String, Object> userMap = Map.of(
            "country", "Maroc",
            "direction", "DIR DES OPERATIONS",
            "job", "IN9009",
            "prod", 1,
            "profile", "COORDINATOR",
            "registration_number", "073176"
    );

    public QueryController(EvaluatorService evaluatorService) {
        this.evaluatorService = evaluatorService;
    }

    @Override
    public ResponseEntity<Object> evaluateQuery(Query query) {
        return ResponseEntity.ok(evaluatorService.evaluate(query, userMap));
    }
}
