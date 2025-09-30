package com.oynee.portfolio.partner.app.usecase.store;

import com.oynee.portfolio.partner.domain.store.dto.PartnerStoreDto;
import com.oynee.portfolio.partner.domain.store.port.input.RetrievePartnerStoreUseCase;
import com.oynee.portfolio.partner.domain.store.port.output.LoadPartnerStorePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class RetrievePartnerStoreService implements RetrievePartnerStoreUseCase {
    private final LoadPartnerStorePort loadPartnerStorePort;

    @Override
    public PartnerStoreDto retrieve(Long storeId) {
        return loadPartnerStorePort.loadPartnerStore(storeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }
}
