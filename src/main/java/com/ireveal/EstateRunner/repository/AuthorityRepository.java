package com.ireveal.EstateRunner.repository;

import com.ireveal.EstateRunner.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.repository
 * @Date 04/02/2025
 */

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
