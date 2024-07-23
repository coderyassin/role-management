package org.yascode.role_management.service.impl;

import org.springframework.stereotype.Service;
import org.yascode.role_management.controller.request.SaveFilterRequest;
import org.yascode.role_management.entity.Filter;
import org.yascode.role_management.repository.ApplicationRepository;
import org.yascode.role_management.repository.FilterRepository;
import org.yascode.role_management.service.FilterService;

import java.util.List;
import java.util.Optional;

@Service
public class FilterServiceImpl implements FilterService {
    private final FilterRepository filterRepository;
    private final ApplicationRepository applicationRepository;

    public FilterServiceImpl(FilterRepository filterRepository,
                             ApplicationRepository applicationRepository) {
        this.filterRepository = filterRepository;
        this.applicationRepository = applicationRepository;
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
    public Optional<Filter> getFilter(Long id) {
        return filterRepository.findById(id);
    }

    @Override
    public List<Filter> allFilters() {
        return filterRepository.findAll();
    }
}
