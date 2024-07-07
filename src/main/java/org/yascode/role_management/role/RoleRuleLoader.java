package org.yascode.role_management.role;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class RoleRuleLoader {
    private final ObjectMapper objectMapper;

    public RoleRuleLoader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public RoleRule loadRoleRule(String filePath) throws IOException {
        return objectMapper.readValue(new File(filePath), RoleRule.class);
    }
}
