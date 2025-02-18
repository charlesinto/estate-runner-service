package com.ireveal.EstateRunner.service.impl;

import com.ireveal.EstateRunner.apimodel.request.CreateEstateRequestDTO;
import com.ireveal.EstateRunner.apimodel.request.CreateUserRequestDTO;
import com.ireveal.EstateRunner.apimodel.request.LoginRequest;
import com.ireveal.EstateRunner.apimodel.response.LoginResponse;
import com.ireveal.EstateRunner.entity.UserDTO;
import com.ireveal.EstateRunner.enums.UserAuthenticationStrategy;
import com.ireveal.EstateRunner.exception.InvalidDataException;
import com.ireveal.EstateRunner.exception.ResourceNotFoundException;
import com.ireveal.EstateRunner.model.User;
import com.ireveal.EstateRunner.repository.UserRepository;
import com.ireveal.EstateRunner.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.service.impl
 * @Date 05/02/2025
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final EstateService estateService;
    private final RoleService roleService;
    private final EstateUserRoleService estateUserRoleService;
    private final AuthenticationService authenticationService;

    @Value("${estate-admin-default-role:ADMIN}")
    private String estateAdminDefaultRole;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public LoginResponse signUp(CreateUserRequestDTO userRequestDTO) throws InvalidDataException, ResourceNotFoundException {
        String password = userRequestDTO.getPassword();
        Optional<User> userOptional = userRepository.findByUserName(userRequestDTO.getUserName());
        if (userOptional.isPresent())
            throw new InvalidDataException("Username already exists");
        userRequestDTO.setPassword(authenticationService.encode(password));
        User user = new User();
        BeanUtils.copyProperties(userRequestDTO, user);
        user.setAuthenticationStrategy(UserAuthenticationStrategy.ESTATE_APP);
        user = userRepository.save(user);

        var loginRequest = LoginRequest
                .builder()
                .userName(userRequestDTO.getUserName())
                .password(password).build();

        var estate = estateService.createEstate(
                CreateEstateRequestDTO.builder().name(userRequestDTO.getEstateName()).build()
        );

        var role = roleService.findRoleByName(estateAdminDefaultRole);

        estateUserRoleService.mapEstateUser(estate, user, role);


        return authenticationService.login(loginRequest, estate.getName());
    }

    @Override
    public User getCurrentlyLoggedInUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private UserDTO convertEntityToData(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
}
