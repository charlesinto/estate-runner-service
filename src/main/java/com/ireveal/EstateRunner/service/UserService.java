package com.ireveal.EstateRunner.service;

import com.ireveal.EstateRunner.apimodel.request.CreateUserRequestDTO;
import com.ireveal.EstateRunner.apimodel.request.LoginRequest;
import com.ireveal.EstateRunner.apimodel.response.LoginResponse;
import com.ireveal.EstateRunner.entity.UserDTO;
import com.ireveal.EstateRunner.exception.InvalidDataException;
import com.ireveal.EstateRunner.exception.ResourceNotFoundException;
import com.ireveal.EstateRunner.model.User;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.service
 * @Date 05/02/2025
 */
public interface UserService {
    LoginResponse signUp(CreateUserRequestDTO userRequestDTO) throws InvalidDataException, ResourceNotFoundException;
    User getCurrentlyLoggedInUser();
    LoginResponse login(LoginRequest loginRequest);
}
