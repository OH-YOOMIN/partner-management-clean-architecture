package com.oynee.portfolio.partner.domain.org.port.output;

import com.oynee.portfolio.partner.domain.org.dto.PartnerOrganizationDto;

import java.util.Optional;

public interface LoadOrganizationPort {
    Optional<PartnerOrganizationDto> loadOrganization(Long orgId);
    boolean existsByCode(String code);
}
