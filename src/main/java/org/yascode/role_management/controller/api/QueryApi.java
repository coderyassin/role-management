package org.yascode.role_management.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.yascode.role_management.querybuilder.Query;

public interface QueryApi {
    @PostMapping(value = "/evaluateQuery")
    ResponseEntity<String> evaluateQuery(@RequestBody Query query);
}
