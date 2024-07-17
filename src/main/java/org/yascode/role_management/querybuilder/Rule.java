package org.yascode.role_management.querybuilder;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rule {
    private String id;
    private String field;
    private String type;
    private String input;
    private String operator;
    private Object value;
    private String condition;
    private List<Rule> rules;
}
