package com.ireveal.EstateRunner.service.impl;

import com.ireveal.EstateRunner.apimodel.request.CreateUserRequestDTO;
import com.ireveal.EstateRunner.entity.UserDTO;
import com.ireveal.EstateRunner.enums.UserAuthenticationStrategy;
import com.ireveal.EstateRunner.exception.InvalidDataException;
import com.ireveal.EstateRunner.model.User;
import com.ireveal.EstateRunner.repository.UserRepository;
import com.ireveal.EstateRunner.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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


    @Override
    public UserDTO createUser(CreateUserRequestDTO userRequestDTO) throws InvalidDataException {
        Optional<User> userOptional = userRepository.findByUserName(userRequestDTO.getUserName());
        if(userOptional.isPresent())
            throw new InvalidDataException("Username already exists");
        User user = new User();
        BeanUtils.copyProperties(userRequestDTO, user);
        user.setAuthenticationStrategy(UserAuthenticationStrategy.ESTATE_APP);
        return convertEntityToData(userRepository.save(user));
    }

    @Override
    public User getCurrentlyLoggedInUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private UserDTO convertEntityToData(User user){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
}
