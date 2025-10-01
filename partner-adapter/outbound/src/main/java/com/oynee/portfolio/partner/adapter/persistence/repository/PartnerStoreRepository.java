package com.oynee.portfolio.partner.adapter.persistence.repository;

import com.oynee.portfolio.partner.adapter.persistence.entity.PartnerStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerStoreRepository extends JpaRepository<PartnerStoreEntity, Long> {
}