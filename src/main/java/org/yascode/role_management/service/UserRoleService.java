package org.yascode.role_management.service;

import java.io.IOException;
import java.util.Map;

public interface UserRoleService {
    String assignRoleToUser(Map<String, Object> userAttributes) throws IOException;
}
