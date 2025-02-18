package com.ireveal.EstateRunner.service;

import com.ireveal.EstateRunner.apimodel.request.CreateUserRequestDTO;
import com.ireveal.EstateRunner.apimodel.request.LoginRequest;
import com.ireveal.EstateRunner.apimodel.response.LoginResponse;
import com.ireveal.EstateRunner.entity.UserDTO;
import com.ireveal.EstateRunner.exception.InvalidDataException;
import com.ireveal.EstateRunner.exception.ResourceNotFoundException;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.service
 * @Date 04/02/2025
 */
public interface AuthenticationService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse login(LoginRequest loginRequest, String estateName);
    String encode(String data);
}
