package org.yascode.role_management.dto;

import lombok.*;
import org.yascode.role_management.util.UserFieldType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ValueUserDto {
    private String field;
    private UserFieldType type;
    private String value;
}
