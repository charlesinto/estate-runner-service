package com.ireveal.EstateRunner.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.model
 * @Date 04/02/2025
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "authorities")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authority extends BaseEntity {
    @Column
    private String name;

}
