package com.ireveal.EstateRunner.apimodel.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.apimodel.request
 * @Date 04/02/2025
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    @NotBlank(message = "username is required")
    private String userName;
    @NotBlank(message = "password is required")
    private String password;
}
