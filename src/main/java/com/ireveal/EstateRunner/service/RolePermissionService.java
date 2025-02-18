package com.ireveal.EstateRunner.service;

import com.ireveal.EstateRunner.model.Role;
import com.ireveal.EstateRunner.model.RolePermission;

import java.util.List;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.service
 * @Date 09/02/2025
 */
public interface RolePermissionService {
    List<RolePermission> getPermissionsByRole(Role role);
}
