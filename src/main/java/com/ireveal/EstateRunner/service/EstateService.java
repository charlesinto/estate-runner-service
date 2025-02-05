package com.ireveal.EstateRunner.service;

import com.ireveal.EstateRunner.apimodel.request.CreateEstateRequestDTO;
import com.ireveal.EstateRunner.entity.EstateDTO;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.service
 * @Date 05/02/2025
 */

public interface EstateService {
    EstateDTO createEstate(CreateEstateRequestDTO createEstateRequestDTO);
    EstateDTO adminSetupEstate(CreateEstateRequestDTO createEstateRequestDTO);
}
