package org.yascode.role_management.service.impl;

import org.springframework.stereotype.Service;
import org.yascode.role_management.role.RoleEvaluator;
import org.yascode.role_management.role.RoleRule;
import org.yascode.role_management.role.RoleRuleLoader;
import org.yascode.role_management.service.UserRoleService;

import java.io.IOException;
import java.util.Map;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final RoleRuleLoader roleRuleLoader;
    private final RoleEvaluator roleEvaluator;

    public UserRoleServiceImpl(RoleRuleLoader roleRuleLoader,
                               RoleEvaluator roleEvaluator) {
        this.roleRuleLoader = roleRuleLoader;
        this.roleEvaluator = roleEvaluator;
    }

    @Override
    public String assignRoleToUser(Map<String, Object> userAttributes) throws IOException {
        String filePath = "C:\\Users\\Yassin\\Technical_Expert\\Stack_Java\\Spring_Security\\Search\\manage-roles\\role-management\\src\\main\\resources\\roles\\ADMIN_ROLE.json";
        RoleRule adminRoleRule = roleRuleLoader.loadRoleRule(filePath);
        if (roleEvaluator.evaluate(adminRoleRule, userAttributes)) {
            return adminRoleRule.getRole();
        }
        return null;
    }
}
