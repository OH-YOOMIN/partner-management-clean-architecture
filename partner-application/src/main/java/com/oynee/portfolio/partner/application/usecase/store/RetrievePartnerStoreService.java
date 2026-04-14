package com.oynee.portfolio.partner.application.usecase.store;

import com.oynee.portfolio.partner.domain.store.dto.PartnerStoreDto;
import com.oynee.portfolio.partner.domain.store.port.input.RetrievePartnerStoreUseCase;
import com.oynee.portfolio.partner.domain.store.port.output.LoadPartnerStorePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class RetrievePartnerStoreService implements RetrievePartnerStoreUseCase {
    private final LoadPartnerStorePort loadPartnerStorePort;

    @Override
    public PartnerStoreDto retrieve(Long storeId) {
        return loadPartnerStorePort.loadPartnerStore(storeId)
                .orElseThrow(() -> new NoSuchElementException("매장을 찾을 수 없습니다: " + storeId));
    }
}
