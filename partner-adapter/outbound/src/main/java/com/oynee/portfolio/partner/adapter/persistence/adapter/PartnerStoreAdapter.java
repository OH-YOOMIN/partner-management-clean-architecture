package com.oynee.portfolio.partner.adapter.persistence.adapter;

import com.oynee.portfolio.partner.domain.store.dto.PartnerStoreDto;
import com.oynee.portfolio.partner.domain.store.model.PartnerStore;
import com.oynee.portfolio.partner.domain.store.port.output.LoadPartnerStorePort;
import com.oynee.portfolio.partner.domain.store.port.output.SavePartnerStorePort;
import com.oynee.portfolio.partner.adapter.persistence.entity.PartnerStoreEntity;
import com.oynee.portfolio.partner.adapter.persistence.mapper.PartnerStoreEntityMapper;
import com.oynee.portfolio.partner.adapter.persistence.repository.PartnerStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PartnerStoreAdapter implements SavePartnerStorePort, LoadPartnerStorePort {
    private final PartnerStoreRepository partnerStoreRepository;
    private final PartnerStoreEntityMapper partnerStoreEntityMapper;

    @Override
    public void savePartnerStore(PartnerStore partnerStore, String createdBy) {
        partnerStoreRepository.save(
                PartnerStoreEntity.from(partnerStore, createdBy, createdBy)
        );
    }

    @Override
    public Optional<PartnerStoreDto> loadPartnerStore(Long storeId) {
        return partnerStoreRepository.findById(storeId).map(partnerStoreEntityMapper::toDto);
    }

    @Override
    public boolean existsByCode(String code) {
        return partnerStoreRepository.existsByCode(code);
    }
}
