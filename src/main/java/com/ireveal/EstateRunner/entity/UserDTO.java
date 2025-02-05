package com.ireveal.EstateRunner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.enums
 * @Date 05/02/2025
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String username;
}
