package com.oynee.portfolio.partner.domain.store.port.output;

import com.oynee.portfolio.partner.domain.store.dto.PartnerStoreDto;

import java.util.Optional;

public interface LoadPartnerStorePort {
    Optional<PartnerStoreDto> loadPartnerStore(Long storeId);
}
