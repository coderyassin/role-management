package org.yascode.role_management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.role_management.controller.api.FilterApi;
import org.yascode.role_management.controller.request.SaveFilterRequest;
import org.yascode.role_management.service.FilterService;

@RestController
@RequestMapping(value = "/filters")
public class FilterController implements FilterApi {
    private final FilterService filterService;

    public FilterController(FilterService filterService) {
        this.filterService = filterService;
    }

    @Override
    public ResponseEntity<?> saveFilter(SaveFilterRequest saveFilterRequest) {
        return ResponseEntity.ok(filterService.saveFilter(saveFilterRequest));
    }

    @Override
    public ResponseEntity<?> retrieveFilterByAuthority(String authority) {
        return ResponseEntity.ok(filterService.getFilterByAuthority(authority));
    }

    @Override
    public ResponseEntity<?> allFilters() {
        return ResponseEntity.ok(filterService.globalFilters());
    }
}
