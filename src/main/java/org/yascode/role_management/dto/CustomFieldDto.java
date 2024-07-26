package org.yascode.role_management.dto;

import lombok.*;
import org.yascode.role_management.entity.Operator;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CustomFieldDto {
    private String field;
    private String type;
    private String input;
    private Set<OperatorDto> operators;
    private Set<OptionDto> options;
}
