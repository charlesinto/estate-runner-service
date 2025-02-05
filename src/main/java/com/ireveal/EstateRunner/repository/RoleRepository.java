package com.ireveal.EstateRunner.repository;

import com.ireveal.EstateRunner.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.repository
 * @Date 04/02/2025
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(String name);
    Optional<Role> findByCode(String code);
}
