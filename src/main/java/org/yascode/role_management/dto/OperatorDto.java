package org.yascode.role_management.dto;

import lombok.*;
import org.yascode.role_management.util.OperatorEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OperatorDto {
    private OperatorEnum name;
}
