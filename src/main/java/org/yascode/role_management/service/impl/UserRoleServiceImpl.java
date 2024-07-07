package org.yascode.role_management.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.yascode.role_management.User;
import org.yascode.role_management.role.RoleEvaluator;
import org.yascode.role_management.role.RoleRule;
import org.yascode.role_management.role.RoleRuleLoader;
import org.yascode.role_management.service.UserRoleService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final RoleRuleLoader roleRuleLoader;
    private final RoleEvaluator roleEvaluator;
    private final ObjectMapper objectMapper;
    private List<RoleRule> roleRules;

    public UserRoleServiceImpl(RoleRuleLoader roleRuleLoader,
                               RoleEvaluator roleEvaluator,
                               ObjectMapper objectMapper) {
        this.roleRuleLoader = roleRuleLoader;
        this.roleEvaluator = roleEvaluator;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() throws IOException {
        this.roleRules = roleRuleLoader.loadRoleRules();
    }

    @Override
    public List<String> assignRolesToUser(User user) {
        List<String> assignedRoles = new ArrayList<>();
        Map<String, Object> userAttributes = objectMapper.convertValue(user, new TypeReference<>() {});
        for (RoleRule roleRule : roleRules) {
            if (roleEvaluator.evaluate(roleRule, userAttributes)) {
                assignedRoles.add(roleRule.getRole());
            }
        }
        return assignedRoles;
    }
}
