package com.ireveal.EstateRunner.service;

import com.ireveal.EstateRunner.apimodel.request.LoginRequest;
import com.ireveal.EstateRunner.apimodel.response.LoginResponse;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.service
 * @Date 04/02/2025
 */
public interface AuthenticationService {
    LoginResponse login(LoginRequest loginRequest);
}
