package com.ireveal.EstateRunner.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ireveal.EstateRunner.enums.UserAuthenticationStrategy;
import com.ireveal.EstateRunner.model.*;
import com.ireveal.EstateRunner.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

import static com.ireveal.EstateRunner.constants.StringConstants.SUPER_ADMIN;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.config
 * @Date 04/02/2025
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class SuperAdminConfig implements ApplicationListener<ApplicationReadyEvent> {
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final BackofficeUserRoleRepository backofficeUserRoleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${estate-runner.super-user}")
    private String superUserName;

    @Value("${estate-runner.super-user-password}")
    private String superUserPassword;

    @Value("${estate-runner.super-user-phone}")
    private String superUserPhoneNumber;

    @Value("${estate-runner.super-user-email}")
    private String superUserEmail;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Admin user setup");
        loadAppPermissions();
        setupSuperAdminPermission();
        createSuperUserAccountIfNotExists();
    }


    @Transactional
    private void setupSuperAdminPermission() {
        Optional<Role> superAdminRoleOptional = roleRepository.findByName(SUPER_ADMIN);
        Role superAdminRole = superAdminRoleOptional.orElseGet(() -> roleRepository.save(Role.builder().name(SUPER_ADMIN).build()));

        List<Authority> permissions = authorityRepository.findAll();
        List<RolePermission> rolePermissionList = rolePermissionRepository.findAllByRole(superAdminRole);

        if (rolePermissionList.size() == permissions.size()) return;


        List<RolePermission> rolePermissions = new ArrayList<>();

        for (Authority authority : permissions) {
            rolePermissions.add(
                    RolePermission.builder()
                            .role(superAdminRole)
                            .authority(authority)
                            .build()
            );
        }

        rolePermissionRepository.saveAll(rolePermissions);

    }

    @Transactional
    private void createSuperUserAccountIfNotExists() {
        Optional<User> userOptional = userRepository.findByUserName(superUserName);
        if (userOptional.isPresent()) return;

        User user = userRepository.save(
                User.builder()
                        .password(passwordEncoder.encode(superUserPassword))
                        .userName(superUserName)
                        .firstName("Super")
                        .lastName("Admin")
                        .authenticationStrategy(UserAuthenticationStrategy.BACKOFFICE)
                        .phoneNumber(superUserPhoneNumber)
                        .email(superUserEmail)
                        .build()
        );

        Optional<Role> superAdminRoleOptional = roleRepository.findByName(SUPER_ADMIN);

        if (superAdminRoleOptional.isEmpty()) return;

        backofficeUserRoleRepository.save(
                BackofficeUserRole.builder().user(user).role(superAdminRoleOptional.get()).build()
        );
    }

    private void loadAppPermissions() {
        ClassPathResource resource = new ClassPathResource("bootstrap/permissions.json");
        try {

            ObjectMapper objectMapper = new ObjectMapper();

            List<String> permissionList = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<String>>(){});

           Set<Authority> authorityList = new HashSet<>();

           for(String permission : permissionList){
               if(authorityRepository.findByName(permission).isPresent()) continue;

               authorityList.add(Authority.builder().name(permission).build());
           }

           authorityRepository.saveAll(authorityList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
