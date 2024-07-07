package org.yascode.role_management;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yascode.role_management.service.UserRoleService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class RoleManagementApplication implements CommandLineRunner {
	private final UserRoleService userRoleService;

    public RoleManagementApplication(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    public static void main(String[] args) {
		SpringApplication.run(RoleManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Map<String, Object> userAttributes = new HashMap<>();
		userAttributes.put("age", 26);
		userAttributes.put("seniority", 4);
		userAttributes.put("profile", "MANAGER_C1");
		userAttributes.put("department", "IT");

		List<String> rules = userRoleService.assignRolesToUser(userAttributes);
		rules.forEach(System.out::println);
	}
}
