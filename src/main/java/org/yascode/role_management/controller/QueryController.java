package org.yascode.role_management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.role_management.controller.api.QueryApi;
import org.yascode.role_management.querybuilder.Query;
import org.yascode.role_management.querybuilder.QueryEvaluator;

import java.util.Map;

@RestController
@RequestMapping(value = "/queries")
public class QueryController implements QueryApi {
    private final QueryEvaluator queryEvaluator;
    private final Map<String, Object> userMap = Map.of(
            "country", "Maroc",
            "direction", "DIR DES OPERATIONS",
            "job", "IN9009",
            "prod", 1,
            "profile", "COORDINATOR",
            "uo", 200
    );

    public QueryController(QueryEvaluator queryEvaluator) {
        this.queryEvaluator = queryEvaluator;
    }

    @Override
    public ResponseEntity<String> evaluateQuery(Query query) {
        return ResponseEntity.ok(queryEvaluator.evaluate(query, userMap) ? "true" : "false");
    }
}
