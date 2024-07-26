package org.yascode.role_management.service;

import org.yascode.role_management.controller.request.SaveFilterRequest;
import org.yascode.role_management.dto.CustomFieldDto;
import org.yascode.role_management.entity.CustomField;
import org.yascode.role_management.entity.Filter;

import java.util.List;
import java.util.Optional;

public interface FilterService {
    Filter saveFilter(SaveFilterRequest saveFilterRequest);

    Optional<Filter> getFilterByAuthority(String authority);

    List<Filter> allFilters();

    List<CustomFieldDto> globalFilters();
}
