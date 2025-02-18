package com.ireveal.EstateRunner.service;

import com.ireveal.EstateRunner.model.Estate;
import com.ireveal.EstateRunner.model.EstateUserRole;
import com.ireveal.EstateRunner.model.Role;
import com.ireveal.EstateRunner.model.User;

import java.util.Optional;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.service
 * @Date 09/02/2025
 */

public interface EstateUserRoleService {
    void mapEstateUser(Estate estate, User user, Role role);

    Optional<EstateUserRole> findFirstByUser(User user);

    Optional<EstateUserRole> findByEstateAndUser(Estate estate, User user);
}
