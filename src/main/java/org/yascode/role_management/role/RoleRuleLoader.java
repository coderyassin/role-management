package org.yascode.role_management.role;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoleRuleLoader {
    @Value("${application.roles.directoryRules}")
    private String directoryRules;
    private final ObjectMapper objectMapper;

    public RoleRuleLoader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<RoleRule> loadRoleRules() throws IOException {
        List<RoleRule> roleRules = new ArrayList<>();
        File directory = new File(directoryRules);
        if (directory.isDirectory()) {
            for (File file : directory.listFiles((dir, name) -> name.endsWith(".json"))) {
                RoleRule roleRule = objectMapper.readValue(file, RoleRule.class);
                roleRules.add(roleRule);
            }
        }
        return roleRules;
    }
}
