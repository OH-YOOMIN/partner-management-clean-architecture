package com.oynee.portfolio.partner.app.usecase.partnership;

import com.oynee.portfolio.partner.domain.partnership.dto.PartnershipDto;
import com.oynee.portfolio.partner.domain.partnership.port.input.RetrievePartnershipUseCase;
import com.oynee.portfolio.partner.domain.partnership.port.output.LoadPartnershipPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class RetrievePartnershipService implements RetrievePartnershipUseCase {
    private final LoadPartnershipPort  loadPartnershipPort;

    @Override
    public PartnershipDto retrieve(Long partnershipId) {
        return loadPartnershipPort.loadPartnership(partnershipId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
