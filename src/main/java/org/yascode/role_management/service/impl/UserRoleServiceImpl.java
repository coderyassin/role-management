package org.yascode.role_management.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
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
    private List<RoleRule> roleRules;

    public UserRoleServiceImpl(RoleRuleLoader roleRuleLoader,
                               RoleEvaluator roleEvaluator) {
        this.roleRuleLoader = roleRuleLoader;
        this.roleEvaluator = roleEvaluator;
    }

    @PostConstruct
    public void init() throws IOException {
        this.roleRules = roleRuleLoader.loadRoleRules();
    }

    @Override
    public List<String> assignRolesToUser(Map<String, Object> userAttributes) {
        List<String> assignedRoles = new ArrayList<>();
        for (RoleRule roleRule : roleRules) {
            if (roleEvaluator.evaluate(roleRule, userAttributes)) {
                assignedRoles.add(roleRule.getRole());
            }
        }
        return assignedRoles;
    }
}
