package com.oynee.portfolio.partner.application.usecase.organization;

import com.oynee.portfolio.partner.domain.org.dto.PartnerOrganizationDto;
import com.oynee.portfolio.partner.domain.org.port.input.RetrievePartnerOrganizationUseCase;
import com.oynee.portfolio.partner.domain.org.port.output.LoadOrganizationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class RetrievePartnerOrganizationService implements RetrievePartnerOrganizationUseCase {
    private final LoadOrganizationPort loadOrganizationPort;

    @Override
    public PartnerOrganizationDto retrieve(Long orgId) {
        return loadOrganizationPort.loadOrganization(orgId)
                .orElseThrow(() -> new NoSuchElementException("조직을 찾을 수 없습니다: " + orgId));
    }
}
