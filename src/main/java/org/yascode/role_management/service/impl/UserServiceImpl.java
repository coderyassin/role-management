package org.yascode.role_management.service.impl;

import org.springframework.stereotype.Service;
import org.yascode.role_management.dto.ValueUserDto;
import org.yascode.role_management.repository.UserRepository;
import org.yascode.role_management.repository.UserValueRepository;
import org.yascode.role_management.service.EvaluatorService;
import org.yascode.role_management.service.FilterService;
import org.yascode.role_management.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final EvaluatorService evaluatorService;
    private final FilterService filterService;
    private final UserRepository userRepository;
    private final UserValueRepository userValueRepository;

    public UserServiceImpl(EvaluatorService evaluatorService,
                           FilterService filterService,
                           UserRepository userRepository,
                           UserValueRepository userValueRepository) {
        this.evaluatorService = evaluatorService;
        this.filterService = filterService;
        this.userRepository = userRepository;
        this.userValueRepository = userValueRepository;
    }

    @Override
    public List<String> getAuthorities(String username) {
        List<String> authorities = new ArrayList<>();
        userRepository.findByUsername(username)
                .ifPresent(user -> {
                    Map<String, Object> valueMap = new HashMap<>();
                    List<ValueUserDto> valuesUser = new ArrayList<>();  //valueMap.put(fieldValue.getField(), fieldValue.getValue())
                    userValueRepository.findUserValueByUser(user)
                            .forEach(fieldValue -> valuesUser.add(ValueUserDto.builder()
                                    .field(fieldValue.getField())
                                    .type(fieldValue.getType())
                                    .value(fieldValue.getValue())
                                    .build() ));

                    filterService.allFilters()
                            .forEach(filter -> {
                                if (evaluatorService.evaluate(filter.getQuery(), valuesUser).isValid()) {
                                    authorities.add(filter.getAuthority());
                                }
                            });
                });
        return authorities;
    }
}
