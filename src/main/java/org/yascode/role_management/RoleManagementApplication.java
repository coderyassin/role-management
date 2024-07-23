package org.yascode.role_management;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.yascode.role_management.entity.User;
import org.yascode.role_management.entity.UserValue;
import org.yascode.role_management.repository.AttributeRepository;
import org.yascode.role_management.repository.UserRepository;
import org.yascode.role_management.repository.UserValueRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
public class RoleManagementApplication implements CommandLineRunner {
	private final AttributeRepository attributeRepository;
	private final UserRepository userRepository;
	private final UserValueRepository userValueRepository;

	public RoleManagementApplication(AttributeRepository attributeRepository,
									 UserRepository userRepository,
									 UserValueRepository userValueRepository) {
		this.attributeRepository = attributeRepository;
		this.userRepository = userRepository;
		this.userValueRepository = userValueRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(RoleManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*User user = userRepository.findByUsername("073176").get();
		Map<String, String> values = new HashMap<>();
		for (UserValue userValue : user.getValues()) {
			values.put(userValue.getAttribute().getField(), userValue.getValue());
		}

		int i = 0;*/
	}
}
