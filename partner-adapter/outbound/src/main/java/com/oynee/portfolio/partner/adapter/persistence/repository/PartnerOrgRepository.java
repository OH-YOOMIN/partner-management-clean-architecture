package com.oynee.portfolio.partner.adapter.persistence.repository;

import com.oynee.portfolio.partner.adapter.persistence.entity.PartnerOrgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerOrgRepository extends JpaRepository<PartnerOrgEntity, Long> {
    boolean existsByCode(String code);
}