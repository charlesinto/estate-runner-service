package com.ireveal.EstateRunner.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.model
 * @Date 05/02/2025
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "estate_user_role")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstateUserRole extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "estate_id")
    private Estate estate;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    private Role role;
}
