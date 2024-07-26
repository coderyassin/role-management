package org.yascode.role_management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "custom_fields")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CustomField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_id")
    private Long id;
    private String field;
    private String type;
    private String input;
    @ManyToMany
    @JoinTable(
            name = "operator_field",
            joinColumns = @JoinColumn(name = "field_id"),
            inverseJoinColumns = @JoinColumn(name = "operator_id")
    )
    private Set<Operator> operators;

    @OneToMany(mappedBy = "customField")
    private Set<Option> options;
}
