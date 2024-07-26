package org.yascode.role_management.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.yascode.role_management.controller.request.SaveFilterRequest;

public interface FilterApi {
    @PostMapping(value = "/saveFilter")
    ResponseEntity<?> saveFilter(@RequestBody SaveFilterRequest saveFilterRequest);

    @GetMapping(value = "/{authority}")
    ResponseEntity<?> retrieveFilterByAuthority(@PathVariable("authority") String authority);

    @GetMapping(value = "/all")
    ResponseEntity<?> allFilters();
}
