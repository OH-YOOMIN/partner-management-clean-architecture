package com.oynee.portfolio.partner.application.usecase.partnership;

import com.oynee.portfolio.partner.domain.partnership.dto.PartnershipDto;
import com.oynee.portfolio.partner.domain.partnership.port.input.RetrievePartnershipUseCase;
import com.oynee.portfolio.partner.domain.partnership.port.output.LoadPartnershipPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class RetrievePartnershipService implements RetrievePartnershipUseCase {
    private final LoadPartnershipPort loadPartnershipPort;

    @Override
    public PartnershipDto retrieve(Long partnershipId) {
        return loadPartnershipPort.loadPartnership(partnershipId)
                .orElseThrow(() -> new NoSuchElementException("제휴를 찾을 수 없습니다: " + partnershipId));
    }
}
