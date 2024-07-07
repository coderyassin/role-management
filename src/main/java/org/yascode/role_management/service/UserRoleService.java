package org.yascode.role_management.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserRoleService {
    List<String> assignRolesToUser(Map<String, Object> userAttributes);
}
