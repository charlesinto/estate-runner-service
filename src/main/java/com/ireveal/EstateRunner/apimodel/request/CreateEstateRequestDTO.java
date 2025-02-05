package com.ireveal.EstateRunner.apimodel.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.apimodel.request
 * @Date 05/02/2025
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEstateRequestDTO {
    private String name;
    private String address;
    private String city;
    private String contactPhone;
    private String contactEmail;
    private String state;
}
