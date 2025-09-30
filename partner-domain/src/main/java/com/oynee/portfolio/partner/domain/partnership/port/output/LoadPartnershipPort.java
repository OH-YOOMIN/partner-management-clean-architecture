package com.oynee.portfolio.partner.domain.partnership.port.output;

import com.oynee.portfolio.partner.domain.partnership.dto.PartnershipDto;

import java.util.Optional;

public interface LoadPartnershipPort {
    Optional<PartnershipDto> loadPartnership(Long partnershipId);
}
