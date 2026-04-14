package com.oynee.portfolio.partner.domain.org.exception;

import com.oynee.portfolio.partner.domain.exception.PartnerDomainException;

public class OrganizationNotFoundException extends PartnerDomainException {
    public OrganizationNotFoundException(Long orgId) {
        super("파트너 조직을 찾을 수 없습니다: " + orgId);
    }
}
