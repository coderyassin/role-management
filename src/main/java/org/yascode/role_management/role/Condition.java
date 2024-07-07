package org.yascode.role_management.role;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Condition {
    private String field;
    private String operator;
    private Object value;
}
