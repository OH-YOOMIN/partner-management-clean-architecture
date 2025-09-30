package com.oynee.portfolio.partner.domain.store.port.input;

import com.oynee.portfolio.partner.domain.store.dto.PartnerStoreDto;

public interface RetrievePartnerStoreUseCase {
    PartnerStoreDto retrieve(Long storeId);
}
