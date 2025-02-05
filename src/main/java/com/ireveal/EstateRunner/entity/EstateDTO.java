package com.ireveal.EstateRunner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.entity
 * @Date 05/02/2025
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstateDTO {
    private String id;
    private String code;
    private String name;
    private String address;
    private String city;
    private String contactPhone;
    private String contactEmail;
    private String state;
    private String logoUrl;
}
