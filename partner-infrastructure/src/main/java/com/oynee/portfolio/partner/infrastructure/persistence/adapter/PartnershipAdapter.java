package com.oynee.portfolio.partner.infrastructure.persistence.adapter;

import com.oynee.portfolio.partner.domain.partnership.dto.PartnershipDto;
import com.oynee.portfolio.partner.domain.partnership.model.Partnership;
import com.oynee.portfolio.partner.domain.partnership.port.output.LoadPartnershipPort;
import com.oynee.portfolio.partner.domain.partnership.port.output.SavePartnershipPort;
import com.oynee.portfolio.partner.infrastructure.persistence.repository.PartnershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PartnershipAdapter implements SavePartnershipPort, LoadPartnershipPort {
    private final PartnershipRepository partnershipRepository;

    @Override
    public void savePartnership(Partnership partnership) {
    }

    @Override
    public Optional<PartnershipDto> loadPartnership(Long partnershipId) {
        return partnershipRepository.findPartnership(partnershipId);
    }

}
