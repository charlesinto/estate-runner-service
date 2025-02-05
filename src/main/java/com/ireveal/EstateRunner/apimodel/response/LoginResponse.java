package com.ireveal.EstateRunner.apimodel.response;

import lombok.*;

import java.util.List;
import java.util.Set;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.apimodel.response
 * @Date 04/02/2025
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String firstName;
    private String lastName;
    private String userName;
    private String token;
    private List<String> authorities;
}
