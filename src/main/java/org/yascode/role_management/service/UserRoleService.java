package org.yascode.role_management.service;

import org.yascode.role_management.User;

import java.util.List;

public interface UserRoleService {
    List<String> assignRolesToUser(User user);
}
