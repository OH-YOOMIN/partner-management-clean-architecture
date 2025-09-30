package com.oynee.portfolio.partner.domain.org.port.input;

import com.oynee.portfolio.partner.domain.org.dto.PartnerOrganizationDto;

public interface RetrievePartnerOrganizationUseCase {
    PartnerOrganizationDto retrieve(Long orgId);
}
