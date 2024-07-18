package org.yascode.role_management.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evaluation {
    private boolean valid;
    private List<String> errors;
}
