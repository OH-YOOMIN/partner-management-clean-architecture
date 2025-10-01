package com.oynee.portfolio.partner.adapter.persistence.repository;

import com.oynee.portfolio.partner.adapter.persistence.entity.PartnershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnershipRepository extends JpaRepository<PartnershipEntity, Long>, PartnershipRepositoryCustom {
}