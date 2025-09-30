package com.oynee.portfolio.partner.infrastructure.persistence.repository;

import com.oynee.portfolio.partner.infrastructure.persistence.entity.PartnerOrgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerOrgRepository extends JpaRepository<PartnerOrgEntity, Long> {
    boolean existsByCode(String code);
}