package com.ireveal.EstateRunner.model;

import com.ireveal.EstateRunner.enums.UserAuthenticationStrategy;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.model
 * @Date 03/02/2025
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity implements UserDetails {


    @Column
    private String firstName;
    @Column
    private String lastName;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column
    private String phoneNumber;

    @Column
    private String password;

    @Column
    private String email;

    @Column(name = "authentication_strategy", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserAuthenticationStrategy authenticationStrategy = UserAuthenticationStrategy.ESTATE_APP;

    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired = true;

    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked = true;

    @Column(name = "is_credentials_non_expired")
    private boolean isCredentialsNonExpired = true;

    @Column(name = "is_enabled")
    private boolean isEnabled = true;

    private Set<SimpleGrantedAuthority> authorities = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return  authorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }
}
