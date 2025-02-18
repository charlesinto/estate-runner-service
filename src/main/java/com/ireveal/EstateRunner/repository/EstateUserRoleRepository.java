package com.ireveal.EstateRunner.repository;

import com.ireveal.EstateRunner.model.Estate;
import com.ireveal.EstateRunner.model.EstateUserRole;
import com.ireveal.EstateRunner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.repository
 * @Date 09/02/2025
 */

@Repository
public interface EstateUserRoleRepository extends JpaRepository<EstateUserRole, String> {
    Optional<EstateUserRole> findFirstByUser(User user);

    Optional<EstateUserRole> findByEstateAndUser(Estate estate, User user);
}
