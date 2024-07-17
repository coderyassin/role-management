package org.yascode.role_management.querybuilder;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String profile;
    private String team;
    private String site;
}
