package com.ireveal.EstateRunner.controller;

import com.ireveal.EstateRunner.annotations.WrapResponse;
import com.ireveal.EstateRunner.apimodel.request.CreateUserRequestDTO;
import com.ireveal.EstateRunner.apimodel.request.LoginRequest;
import com.ireveal.EstateRunner.apimodel.response.LoginResponse;
import com.ireveal.EstateRunner.entity.UserDTO;
import com.ireveal.EstateRunner.exception.InvalidDataException;
import com.ireveal.EstateRunner.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.controller
 * @Date 04/02/2025
 */

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/auth")
@WrapResponse
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("login")
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest){
        return authenticationService.login(loginRequest);
    }

    @PostMapping("signup")
    public UserDTO signupUser(@RequestBody @Valid CreateUserRequestDTO createUserRequestDTO) throws InvalidDataException {
        return  authenticationService.signupUser(createUserRequestDTO);
    }
}
