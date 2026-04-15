package com.oynee.portfolio.partner.adapter.persistence.adapter;

import com.oynee.portfolio.partner.domain.partnership.dto.PartnershipDto;
import com.oynee.portfolio.partner.domain.partnership.model.Partnership;
import com.oynee.portfolio.partner.domain.partnership.port.output.LoadPartnershipPort;
import com.oynee.portfolio.partner.domain.partnership.port.output.SavePartnershipPort;
import com.oynee.portfolio.partner.adapter.persistence.entity.PartnershipEntity;
import com.oynee.portfolio.partner.adapter.persistence.repository.PartnershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PartnershipAdapter implements SavePartnershipPort, LoadPartnershipPort {
    private final PartnershipRepository partnershipRepository;

    @Override
    public void savePartnership(Partnership partnership, String createdBy) {
        partnershipRepository.save(
                PartnershipEntity.from(partnership, createdBy, createdBy)
        );
    }

    @Override
    public Optional<PartnershipDto> loadPartnership(Long partnershipId) {
        return partnershipRepository.findPartnership(partnershipId);
    }

    @Override
    public boolean existsByPartnerOrgId(Long partnerOrgId) {
        return partnershipRepository.existsByPartnerOrgId(partnerOrgId);
    }

    @Override
    public boolean existsByPartnerStoreId(Long partnerStoreId) {
        return partnershipRepository.existsByPartnerStoreId(partnerStoreId);
    }
}
