package com.ireveal.EstateRunner.service;

import com.ireveal.EstateRunner.entity.RoleDTO;
import com.ireveal.EstateRunner.exception.BadRequestException;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.service
 * @Date 05/02/2025
 */
public interface RoleService {
    RoleDTO createRole(RoleDTO roleDTO) throws BadRequestException;
}
