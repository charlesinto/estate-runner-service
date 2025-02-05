package com.ireveal.EstateRunner.repository;

import com.ireveal.EstateRunner.model.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.repository
 * @Date 05/02/2025
 */

@Repository
public interface EstateRepository extends JpaRepository<Estate, String> {
    Optional<Estate> findByCode(String code);
}
