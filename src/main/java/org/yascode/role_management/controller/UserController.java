package org.yascode.role_management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.role_management.controller.api.UserApi;
import org.yascode.role_management.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController implements UserApi {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> allAuthorities(String username) {
        return ResponseEntity.ok(userService.getAuthorities(username));
    }
}
