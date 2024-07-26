package org.yascode.role_management.entity;

import jakarta.persistence.*;
import lombok.*;
import org.yascode.role_management.converter.QueryAttributeConverter;
import org.yascode.role_management.querybuilder.Query;

@Entity
@Table(name = "filters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Filter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;
    private String authority;
    @Convert(converter = QueryAttributeConverter.class)
    @Column(columnDefinition = "TEXT")
    private Query query;
}
