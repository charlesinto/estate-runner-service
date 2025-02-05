package com.ireveal.EstateRunner.service;

import com.ireveal.EstateRunner.apimodel.request.CreateUserRequestDTO;
import com.ireveal.EstateRunner.entity.UserDTO;
import com.ireveal.EstateRunner.exception.InvalidDataException;
import com.ireveal.EstateRunner.model.User;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.service
 * @Date 05/02/2025
 */
public interface UserService {
    UserDTO createUser(CreateUserRequestDTO userRequestDTO) throws InvalidDataException;
    User getCurrentlyLoggedInUser();
}
