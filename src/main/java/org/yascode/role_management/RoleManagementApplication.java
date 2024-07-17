package org.yascode.role_management;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yascode.role_management.service.UserRoleService;

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
		/*User user = User.builder()
				.id(1L)
				.username("yascode")
				.age(38)
				.seniority(4)
				.profile("MANAGER_C3")
				.department("IT")
				.build();

		userRoleService.assignRolesToUser(user)
					   .forEach(System.out::println);*/
	}
}
