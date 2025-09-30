package com.oynee.portfolio.partner.domain.partnership.port.input;

import com.oynee.portfolio.partner.domain.partnership.dto.PartnershipDto;

public interface RetrievePartnershipUseCase {
    PartnershipDto retrieve(Long partnershipId);
}
