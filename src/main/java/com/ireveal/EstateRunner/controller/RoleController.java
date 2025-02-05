package com.ireveal.EstateRunner.controller;

import com.ireveal.EstateRunner.annotations.WrapResponse;
import com.ireveal.EstateRunner.entity.RoleDTO;
import com.ireveal.EstateRunner.exception.BadRequestException;
import com.ireveal.EstateRunner.service.RoleService;
import com.ireveal.EstateRunner.util.CombinedAuthorityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.controller
 * @Date 05/02/2025
 */

@WrapResponse
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/role")
public class RoleController {
    private final RoleService roleService;

    @PostMapping("create")
    @PreAuthorize(CombinedAuthorityUtil.SA)
    public RoleDTO createRole(@RequestBody @Valid RoleDTO roleDTO) throws BadRequestException {
        return roleService.createRole(roleDTO);
    }
}
