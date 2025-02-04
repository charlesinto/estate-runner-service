package com.ireveal.EstateRunner.repository;

import com.ireveal.EstateRunner.model.Role;
import com.ireveal.EstateRunner.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.repository
 * @Date 04/02/2025
 */

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, String> {
    List<RolePermission> findAllByRole(Role role);

    List<RolePermission> findAllByRole_Name(String name);
}
