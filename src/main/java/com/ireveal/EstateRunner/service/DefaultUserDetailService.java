package com.ireveal.EstateRunner.service;

import com.ireveal.EstateRunner.enums.UserAuthenticationStrategy;
import com.ireveal.EstateRunner.model.BackofficeUserRole;
import com.ireveal.EstateRunner.model.EstateUserRole;
import com.ireveal.EstateRunner.model.Role;
import com.ireveal.EstateRunner.model.User;
import com.ireveal.EstateRunner.repository.BackofficeUserRoleRepository;
import com.ireveal.EstateRunner.repository.RolePermissionRepository;
import com.ireveal.EstateRunner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.AuthorizationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.service
 * @Date 04/02/2025
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final EstateUserRoleService estateUserRoleService;
    private final RolePermissionRepository rolePermissionRepository;
    private final BackofficeUserRoleRepository backofficeUserRoleRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Set<SimpleGrantedAuthority> authorities;
        if (UserAuthenticationStrategy.BACKOFFICE.equals(user.getAuthenticationStrategy())) {
            authorities = new HashSet<>(getAdminGrantedAuthorities(user));
        } else {
            authorities = new HashSet<>(getUserGrantedAuthorities(user));
        }
        user.setAuthorities(authorities);

        return user;
    }

    private List<SimpleGrantedAuthority> getAdminGrantedAuthorities(User user) {
        BackofficeUserRole backofficeUserRole = backofficeUserRoleRepository.findByUser_Id(user.getId()).orElseThrow(() -> new AuthorizationException("You do not have access to this system. Please contact system administrator"));
        return getAuthorities(backofficeUserRole.getRole());
    }

    private List<SimpleGrantedAuthority> getUserGrantedAuthorities(User user) {
        Optional<EstateUserRole> estateUserRoleOptional = estateUserRoleService.findFirstByUser(user);
        if (estateUserRoleOptional.isEmpty())
            return List.of();

        var estateUserRole = estateUserRoleOptional.get();
        return getAuthorities(estateUserRole.getRole());
    }

    private List<SimpleGrantedAuthority> getAuthorities(Role role) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = rolePermissionRepository.findAllByRole_Name(role.getName())
                .stream().map(item -> new SimpleGrantedAuthority(item.getAuthority().getName())
                ).collect(Collectors.toList());

        simpleGrantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        return simpleGrantedAuthorityList;
    }
}
