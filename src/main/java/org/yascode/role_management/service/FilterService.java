package org.yascode.role_management.service;

import org.yascode.role_management.controller.request.SaveFilterRequest;
import org.yascode.role_management.entity.Filter;

import java.util.List;
import java.util.Optional;

public interface FilterService {
    Filter saveFilter(SaveFilterRequest saveFilterRequest);

    Optional<Filter> getFilter(Long id);

    List<Filter> allFilters();
}
