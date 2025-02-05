package com.ireveal.EstateRunner.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.model
 * @Date 05/02/2025
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "estates")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Estate extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String code;
    @Column
    private String address;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    private String contactEmail;
    @Column
    private String contactPhone;
    @Column
    private String logoUrl;
}
