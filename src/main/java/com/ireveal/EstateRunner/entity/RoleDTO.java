package com.ireveal.EstateRunner.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.entity
 * @Date 05/02/2025
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private String id;
    private String code;
    @NotBlank(message = "role name is required")
    private String name;
}
