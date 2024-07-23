package org.yascode.role_management.querybuilder;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Query {
    private String condition;
    private List<Rule> rules;
    private boolean valid;
}
