package org.yascode.role_management.service;

import java.util.List;

public interface UserService {
    List<String> getAuthorities(String username);
}
