package com.ireveal.EstateRunner.service.impl;

import com.ireveal.EstateRunner.apimodel.request.CreateUserRequestDTO;
import com.ireveal.EstateRunner.apimodel.request.LoginRequest;
import com.ireveal.EstateRunner.apimodel.response.LoginResponse;
import com.ireveal.EstateRunner.entity.UserDTO;
import com.ireveal.EstateRunner.exception.InvalidDataException;
import com.ireveal.EstateRunner.model.User;
import com.ireveal.EstateRunner.service.AuthenticationService;
import com.ireveal.EstateRunner.service.JwtService;
import com.ireveal.EstateRunner.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.service.impl
 * @Date 04/02/2025
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public UserDTO signupUser(CreateUserRequestDTO createUserRequestDTO) throws InvalidDataException {
        createUserRequestDTO.setPassword(passwordEncoder.encode(createUserRequestDTO.getPassword()));
        return userService.createUser(createUserRequestDTO);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (!authentication.isAuthenticated())
            throw new UsernameNotFoundException("User not found");

        return handleLoginRequest(authentication);
    }

    private LoginResponse handleLoginRequest( Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Map<String, Object> claims = new HashMap<>();

        claims.put("authorities", new HashSet<>(getAuthorities(user)));
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());


        String token = jwtService.generateToken(claims, user);

        return LoginResponse
                .builder()
                .token(token)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUsername())
                .authorities(getAuthorities(user))
                .build();
    }

    private static List<String> getAuthorities(UserDetails userDetails) {
        return userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }
}
