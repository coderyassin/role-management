package org.yascode.role_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "options")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "key_option")
    private String keyOption;

    @Column(name = "value_option")
    private String valueOption;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private CustomField customField;
}
