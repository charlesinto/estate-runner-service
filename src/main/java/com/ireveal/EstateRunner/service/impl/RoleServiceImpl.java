package com.ireveal.EstateRunner.service.impl;

import com.ireveal.EstateRunner.entity.RoleDTO;
import com.ireveal.EstateRunner.exception.BadRequestException;
import com.ireveal.EstateRunner.model.Role;
import com.ireveal.EstateRunner.repository.RoleRepository;
import com.ireveal.EstateRunner.service.RoleService;
import com.ireveal.EstateRunner.util.RandomDataUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.service.impl
 * @Date 05/02/2025
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) throws BadRequestException {
        var roleOptional = roleRepository.findByName(roleDTO.getName());
        if (roleOptional.isPresent())
            throw new BadRequestException("Role name already exists");
        var role = convertDataToEntity(roleDTO);

        role.setCode(generateCode());
        return convertEntityToData(roleRepository.save(role));
    }

    private RoleDTO convertEntityToData(Role role) {
        var roleDTO = new RoleDTO();
        BeanUtils.copyProperties(role, roleDTO);
        return roleDTO;
    }

    private Role convertDataToEntity(RoleDTO roleDTO) {
        var role = new Role();
        BeanUtils.copyProperties(roleDTO, role);
        return role;
    }


    private String generateCode() {
        String code = RandomDataUtil.generateRandomCode(5);
        while (roleRepository.findByCode(code).isPresent()) {
            code = RandomDataUtil.generateRandomCode(5);
        }

        return code;
    }
}
