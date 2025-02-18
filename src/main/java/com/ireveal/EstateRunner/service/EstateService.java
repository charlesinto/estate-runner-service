package com.ireveal.EstateRunner.service;

import com.ireveal.EstateRunner.apimodel.request.CreateEstateRequestDTO;
import com.ireveal.EstateRunner.entity.EstateDTO;
import com.ireveal.EstateRunner.model.Estate;

import java.util.Optional;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.service
 * @Date 05/02/2025
 */

public interface EstateService {
    Estate createEstate(CreateEstateRequestDTO createEstateRequestDTO);
    EstateDTO adminSetupEstate(CreateEstateRequestDTO createEstateRequestDTO);

    Optional<Estate> findByCode(String estateCode);
}
