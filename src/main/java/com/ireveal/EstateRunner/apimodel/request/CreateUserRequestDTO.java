package com.ireveal.EstateRunner.apimodel.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequestDTO {
    @NotBlank(message = "first name is required")
    private String firstName;
    @NotBlank(message = "last name is required")
    private String lastName;
    @NotBlank(message = "username is required")
    private String userName;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Phone number required")
    private String phoneNumber;
    @Email(message = "Email is required")
    private String email;
}
