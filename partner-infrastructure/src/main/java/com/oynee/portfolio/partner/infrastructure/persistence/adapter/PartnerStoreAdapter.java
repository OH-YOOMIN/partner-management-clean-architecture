package com.oynee.portfolio.partner.infrastructure.persistence.adapter;

import com.oynee.portfolio.partner.domain.store.dto.PartnerStoreDto;
import com.oynee.portfolio.partner.domain.store.model.PartnerStore;
import com.oynee.portfolio.partner.domain.store.port.output.LoadPartnerStorePort;
import com.oynee.portfolio.partner.domain.store.port.output.SavePartnerStorePort;
import com.oynee.portfolio.partner.infrastructure.persistence.mapper.PartnerStoreEntityMapper;
import com.oynee.portfolio.partner.infrastructure.persistence.repository.PartnerStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PartnerStoreAdapter implements SavePartnerStorePort, LoadPartnerStorePort {
    private final PartnerStoreRepository partnerStoreRepository;
    private final PartnerStoreEntityMapper partnerStoreEntityMapper;

    @Override
    public void savePartnerStore(PartnerStore partnerStore) {

    }

    @Override
    public Optional<PartnerStoreDto> loadPartnerStore(Long storeId) {
        return partnerStoreRepository.findById(storeId).map(partnerStoreEntityMapper::toDto);
    }
}
