package com.oynee.portfolio.partner.app.usecase.organization;

import com.oynee.portfolio.partner.domain.org.dto.PartnerOrganizationDto;
import com.oynee.portfolio.partner.domain.org.port.input.RetrievePartnerOrganizationUseCase;
import com.oynee.portfolio.partner.domain.org.port.output.LoadOrganizationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class RetrievePartnerOrganizationService implements RetrievePartnerOrganizationUseCase {
    private final LoadOrganizationPort loadOrganizationPort;

    @Override
    public PartnerOrganizationDto retrieve(Long orgId) {
        return loadOrganizationPort.loadOrganization(orgId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }
}
