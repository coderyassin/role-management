package org.yascode.role_management.entity;

import jakarta.persistence.*;
import lombok.*;
import org.yascode.role_management.util.UserFieldType;

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

    @Column(name = "field")
    private String field;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private UserFieldType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String value;
}
