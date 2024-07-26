package org.yascode.role_management.service.impl;

import org.springframework.stereotype.Service;
import org.yascode.role_management.controller.request.SaveFilterRequest;
import org.yascode.role_management.dto.CustomFieldDto;
import org.yascode.role_management.dto.OperatorDto;
import org.yascode.role_management.dto.OptionDto;
import org.yascode.role_management.entity.Filter;
import org.yascode.role_management.repository.ApplicationRepository;
import org.yascode.role_management.repository.CustomFieldRepository;
import org.yascode.role_management.repository.FilterRepository;
import org.yascode.role_management.service.FilterService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilterServiceImpl implements FilterService {
    private final FilterRepository filterRepository;
    private final ApplicationRepository applicationRepository;
    private final CustomFieldRepository customFieldRepository;

    public FilterServiceImpl(FilterRepository filterRepository,
                             ApplicationRepository applicationRepository,
                             CustomFieldRepository customFieldRepository) {
        this.filterRepository = filterRepository;
        this.applicationRepository = applicationRepository;
        this.customFieldRepository = customFieldRepository;
    }

    @Override
    public Filter saveFilter(SaveFilterRequest saveFilterRequest) {
        Filter filter = Filter.builder()
                .query(saveFilterRequest.query())
                .application(applicationRepository.findById(saveFilterRequest.application().getId()).get())
                .authority(saveFilterRequest.authority())
                .build();
        return filterRepository.save(filter);
    }

    @Override
    public Optional<Filter> getFilterByAuthority(String authority) {
        return filterRepository.findByAuthority(authority);
    }

    @Override
    public List<Filter> allFilters() {
        return filterRepository.findAll();
    }

    @Override
    public List<CustomFieldDto> globalFilters() {
        return customFieldRepository.findAll()
                .stream()
                .map(customField -> CustomFieldDto.builder()
                        .field(customField.getField())
                        .type(customField.getType())
                        .input(customField.getInput())
                        .options(customField.getOptions()
                                .stream()
                                .map(option -> OptionDto.builder()
                                        .key(option.getKeyOption())
                                        .value(option.getValueOption())
                                        .build())
                                .collect(Collectors.toSet()))
                        .operators(customField.getOperators()
                                .stream()
                                .map(operator -> OperatorDto.builder()
                                        .name(operator.getName())
                                        .build())
                                .collect(Collectors.toSet()))
                        .build())
                .toList();
    }
}
