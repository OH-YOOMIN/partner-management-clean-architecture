package com.oynee.portfolio.partner.domain.org.exception;

import com.oynee.portfolio.partner.domain.exception.PartnerDomainException;

public class DuplicateOrganizationCodeException extends PartnerDomainException {
    public DuplicateOrganizationCodeException(String code) {
        super("이미 사용 중인 조직 코드입니다: " + code);
    }
}
