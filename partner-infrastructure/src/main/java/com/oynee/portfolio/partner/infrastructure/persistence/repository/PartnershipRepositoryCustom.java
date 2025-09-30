package com.oynee.portfolio.partner.infrastructure.persistence.repository;

import com.oynee.portfolio.partner.domain.partnership.dto.PartnershipDto;

import java.util.Optional;

public interface PartnershipRepositoryCustom {
    Optional<PartnershipDto> findPartnership(Long partnershipId);
}
