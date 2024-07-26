package org.yascode.role_management;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yascode.role_management.entity.*;
import org.yascode.role_management.repository.*;
import org.yascode.role_management.util.OperatorEnum;
import org.yascode.role_management.util.UserFieldType;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class RoleManagementApplication implements CommandLineRunner {
	private final UserRepository userRepository;
	private final UserValueRepository userValueRepository;
	private final CustomFieldRepository customFieldRepository;
	private final OperatorRepository operatorRepository;
	private final OptionRepository optionRepository;

	public RoleManagementApplication(UserRepository userRepository,
									 UserValueRepository userValueRepository,
									 CustomFieldRepository customFieldRepository,
									 OperatorRepository operatorRepository,
									 OptionRepository optionRepository) {
		this.userRepository = userRepository;
		this.userValueRepository = userValueRepository;
		this.customFieldRepository = customFieldRepository;
		this.operatorRepository = operatorRepository;
		this.optionRepository = optionRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(RoleManagementApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		//Set<Operator> operators = new HashSet<>();

		/*Arrays.stream(OperatorEnum.values())
				.forEach(operator -> {
					Operator opt = Operator.builder()
							.name(operator)
							.build();
					operators.add(operatorRepository.save(opt));
				});*/

		/*operators = operatorRepository.findAll()
				.stream()
				.collect(Collectors.toSet());*/

		//Optional<Operator> operator = operatorRepository.findByName(OperatorEnum.EQUAL);

		/*CustomField customField = CustomField.builder()
				.field("prod")
				.type("integer")
				.input("radio")
				.operators(Set.of(operator.get()))
				.build();*/

		//customFieldRepository.save(customField);

		/*CustomField prod = customFieldRepository.findByField("prod").get();

		Option option1 = Option.builder()
				.keyOption("0")
				.valueOption("0")
				.customField(prod)
				.build();

		Option option2 = Option.builder()
				.keyOption("1")
				.valueOption("1")
				.customField(prod)
				.build();

		optionRepository.saveAll(List.of(option1, option2));*/

		/*CustomField prod = customFieldRepository.findByField("prod").get();
		Operator operator = operatorRepository.findByName(OperatorEnum.NOT_EQUAL).get();
		Set<Operator> operators = prod.getOperators();
		operators.add(operator);

		prod.setOperators(operators);
		customFieldRepository.save(prod);*/

		/*User user = User.builder()
				.username("073176")
				.build();

		userRepository.save(user);*/

		User user = userRepository.findByUsername("073176").get();

		UserValue userValue1 = UserValue.builder()
				.field("direction")
				.value("SOFTWARE FACTORY")
				.type(UserFieldType.STRING)
				.user(user)
				.build();

		UserValue userValue2 = UserValue.builder()
				.field("job")
				.value("IN14131")
				.type(UserFieldType.STRING)
				.user(user)
				.build();

		UserValue userValue3 = UserValue.builder()
				.field("prod")
				.value("0")
				.type(UserFieldType.INTEGER)
				.user(user)
				.build();

		UserValue userValue4 = UserValue.builder()
				.field("profile")
				.value("COORDINATOR")
				.type(UserFieldType.STRING)
				.user(user)
				.build();

		UserValue userValue5 = UserValue.builder()
				.field("assignment")
				.value("DSI")
				.type(UserFieldType.STRING)
				.user(user)
				.build();

		UserValue userValue6 = UserValue.builder()
				.field("country")
				.value("Maroc")
				.type(UserFieldType.STRING)
				.user(user)
				.build();

		UserValue userValue7 = UserValue.builder()
				.field("registration_number")
				.value("073176")
				.type(UserFieldType.STRING)
				.user(user)
				.build();

		//userValueRepository.saveAll(List.of(userValue7));
	}
}
