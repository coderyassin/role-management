package org.yascode.role_management.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserApi {
    @GetMapping(value = "/authorities/{username}")
    ResponseEntity<?> allAuthorities(@PathVariable("username") String username);
}
