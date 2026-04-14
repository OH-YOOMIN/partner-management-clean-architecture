package com.oynee.portfolio.partner.domain.store.exception;

import com.oynee.portfolio.partner.domain.exception.PartnerDomainException;

public class PartnerStoreNotFoundException extends PartnerDomainException {
    public PartnerStoreNotFoundException(Long storeId) {
        super("파트너 매장을 찾을 수 없습니다: " + storeId);
    }
}
