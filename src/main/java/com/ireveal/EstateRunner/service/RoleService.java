package com.ireveal.EstateRunner.service;

import com.ireveal.EstateRunner.entity.RoleDTO;
import com.ireveal.EstateRunner.exception.BadRequestException;
import com.ireveal.EstateRunner.exception.ResourceNotFoundException;
import com.ireveal.EstateRunner.model.Role;
import com.ireveal.EstateRunner.model.RolePermission;

import java.util.List;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.service
 * @Date 05/02/2025
 */
public interface RoleService {
    RoleDTO createRole(RoleDTO roleDTO) throws BadRequestException;

    List<RolePermission> getPermission(String roleCode) throws ResourceNotFoundException;

    Role findRoleByName(String role) throws ResourceNotFoundException;
}
