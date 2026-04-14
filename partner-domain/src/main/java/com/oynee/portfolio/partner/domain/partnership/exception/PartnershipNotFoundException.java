package com.oynee.portfolio.partner.domain.partnership.exception;

import com.oynee.portfolio.partner.domain.exception.PartnerDomainException;

public class PartnershipNotFoundException extends PartnerDomainException {
    public PartnershipNotFoundException(Long partnershipId) {
        super("제휴 정보를 찾을 수 없습니다: " + partnershipId);
    }
}
