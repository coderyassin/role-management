package org.yascode.role_management.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String username;
    private Integer age;
    private Integer seniority;
    private String profile;
    private String department;
}
