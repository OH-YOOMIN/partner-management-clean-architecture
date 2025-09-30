package com.oynee.portfolio.partner.infrastructure.persistence.adapter;

import com.oynee.portfolio.partner.domain.org.dto.PartnerOrganizationDto;
import com.oynee.portfolio.partner.domain.org.model.PartnerOrganization;
import com.oynee.portfolio.partner.domain.org.port.output.LoadOrganizationPort;
import com.oynee.portfolio.partner.domain.org.port.output.SaveOrganizationPort;
import com.oynee.portfolio.partner.infrastructure.persistence.entity.PartnerOrgEntity;
import com.oynee.portfolio.partner.infrastructure.persistence.mapper.PartnerOrgEntityMapper;
import com.oynee.portfolio.partner.infrastructure.persistence.repository.PartnerOrgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PartnerOrgAdapter implements SaveOrganizationPort, LoadOrganizationPort {
    private final PartnerOrgRepository partnerOrgRepository;
    private final PartnerOrgEntityMapper partnerOrgEntityMapper;

    @Override
    public void saveOrganization(
            PartnerOrganization partnerOrganization,
            String createdBy
    ) {
        partnerOrgRepository.save(
                PartnerOrgEntity.from(
                        partnerOrganization,
                        createdBy,
                        createdBy
                )
        );
    }

    @Override
    public Optional<PartnerOrganizationDto> loadOrganization(Long orgId) {
        return partnerOrgRepository.findById(orgId).map(partnerOrgEntityMapper::toDto);
    }

    @Override
    public boolean existsByCode(String code) {
        return partnerOrgRepository.existsByCode(code);
    }
}
