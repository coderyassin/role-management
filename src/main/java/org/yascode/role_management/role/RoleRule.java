package org.yascode.role_management.role;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleRule {
    private String role;
    private List<Condition> conditions;
}
