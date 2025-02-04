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
@Table(name = "role_permissions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolePermission extends BaseEntity{
    @ManyToOne
    private Authority authority;
    @ManyToOne
    private Role role;
}
