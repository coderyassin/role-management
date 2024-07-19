package org.yascode.role_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users_value")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "attribute_id", nullable = false)
    private Attribute attribute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String value;
}
