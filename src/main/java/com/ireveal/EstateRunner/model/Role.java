package com.ireveal.EstateRunner.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.AuditorAware;

import java.util.HashSet;
import java.util.Set;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.model
 * @Date 04/02/2025
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role extends BaseEntity{
    @Column
    private String name;

    @Column
    private String code;


    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<RolePermission> permissions = new HashSet<>();
}
