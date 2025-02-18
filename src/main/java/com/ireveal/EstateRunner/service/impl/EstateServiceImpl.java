package com.ireveal.EstateRunner.service.impl;

import com.ireveal.EstateRunner.apimodel.request.CreateEstateRequestDTO;
import com.ireveal.EstateRunner.entity.EstateDTO;
import com.ireveal.EstateRunner.model.Estate;
import com.ireveal.EstateRunner.repository.EstateRepository;
import com.ireveal.EstateRunner.service.EstateService;
import com.ireveal.EstateRunner.util.RandomDataUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.service.impl
 * @Date 05/02/2025
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class EstateServiceImpl implements EstateService {
    private final EstateRepository estateRepository;

    @Override
    public Estate createEstate(CreateEstateRequestDTO createEstateRequestDTO) {
        Estate estate = new Estate();
        BeanUtils.copyProperties(createEstateRequestDTO, estate);
        estate.setCode(generateCode());
        return estateRepository.save(estate);
    }

    @Override
    public EstateDTO adminSetupEstate(CreateEstateRequestDTO createEstateRequestDTO) {
        return null;
    }

    @Override
    public Optional<Estate> findByCode(String estateCode) {
        return estateRepository.findByCode(estateCode);
    }

    private EstateDTO convertEntityToData(Estate entity){
        EstateDTO estateDTO = new EstateDTO();
        BeanUtils.copyProperties(entity, estateDTO);
        return  estateDTO;
    }



    private String generateCode(){
        String code = RandomDataUtil.generateRandomCode(5);
        while (estateRepository.findByCode(code).isPresent()){
            code = RandomDataUtil.generateRandomCode(5);
        }

        return code;
    }
}
