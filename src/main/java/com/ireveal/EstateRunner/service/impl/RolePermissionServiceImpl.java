package com.ireveal.EstateRunner.service.impl;

import com.ireveal.EstateRunner.model.Role;
import com.ireveal.EstateRunner.model.RolePermission;
import com.ireveal.EstateRunner.repository.RolePermissionRepository;
import com.ireveal.EstateRunner.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.service.impl
 * @Date 09/02/2025
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class RolePermissionServiceImpl implements RolePermissionService {
    private final RolePermissionRepository rolePermissionRepository;

    @Override
    public List<RolePermission> getPermissionsByRole(Role role) {
        return rolePermissionRepository.findAllByRole(role);
    }
}
