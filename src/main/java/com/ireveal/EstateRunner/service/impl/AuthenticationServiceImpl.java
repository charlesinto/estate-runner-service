package com.ireveal.EstateRunner.service.impl;

import com.ireveal.EstateRunner.apimodel.request.LoginRequest;
import com.ireveal.EstateRunner.apimodel.response.LoginResponse;
import com.ireveal.EstateRunner.model.User;
import com.ireveal.EstateRunner.service.AuthenticationService;
import com.ireveal.EstateRunner.service.DefaultUserDetailService;
import com.ireveal.EstateRunner.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final DefaultUserDetailService defaultUserDetailService;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (!authentication.isAuthenticated())
            throw new UsernameNotFoundException("User not found");

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
                .userName(loginRequest.getUserName())
                .authorities(getAuthorities(user))
                .build();
    }

    private static List<String> getAuthorities(UserDetails userDetails) {
        return userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }
}
