package com.ireveal.EstateRunner.service.impl;

import com.ireveal.EstateRunner.model.Estate;
import com.ireveal.EstateRunner.model.EstateUserRole;
import com.ireveal.EstateRunner.model.Role;
import com.ireveal.EstateRunner.model.User;
import com.ireveal.EstateRunner.repository.EstateUserRoleRepository;
import com.ireveal.EstateRunner.service.EstateUserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.service.impl
 * @Date 09/02/2025
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class EstateUserRoleServiceImpl implements EstateUserRoleService {
    private final EstateUserRoleRepository estateUserRoleRepository;

    @Override
    public Optional<EstateUserRole> findFirstByUser(User user) {
        return estateUserRoleRepository.findFirstByUser(user);
    }

    @Override
    public Optional<EstateUserRole> findByEstateAndUser(Estate estate, User user) {
        return estateUserRoleRepository.findByEstateAndUser(estate, user);
    }

    @Override
    public void mapEstateUser(Estate estate, User user, Role role) {
        estateUserRoleRepository.save(
                EstateUserRole
                        .builder()
                        .estate(estate)
                        .role(role)
                        .user(user)
                        .build()
        );
    }
}
