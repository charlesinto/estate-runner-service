package com.ireveal.EstateRunner.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.model
 * @Date 04/02/2025
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "backoffice_user_roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BackofficeUserRole extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    private Role role;
}
