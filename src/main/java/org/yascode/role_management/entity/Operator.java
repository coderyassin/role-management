package org.yascode.role_management.entity;

import jakarta.persistence.*;
import lombok.*;
import org.yascode.role_management.util.OperatorEnum;

@Entity
@Table(name = "operators")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Operator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operator_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private OperatorEnum name;
}
