package com.oynee.portfolio.partner.application.usecase.organization;

import com.oynee.portfolio.partner.domain.org.model.PartnerOrganization;
import com.oynee.portfolio.partner.domain.org.port.output.LoadOrganizationPort;
import com.oynee.portfolio.partner.domain.org.port.input.RegisterPartnerOrganizationUseCase;
import com.oynee.portfolio.partner.domain.org.port.output.SaveOrganizationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterPartnerOrganizationService implements RegisterPartnerOrganizationUseCase {
    private final LoadOrganizationPort loadOrganizationPort;
    private final SaveOrganizationPort saveOrganizationPort;

    @Override
    public void registerOrganization(Command command) {
        if (loadOrganizationPort.existsByCode(command.getCode())) {
            throw new IllegalStateException("이미 사용 중인 조직 코드입니다: " + command.getCode());
        }

        saveOrganizationPort.saveOrganization(
                PartnerOrganization.create(
                        command.getName(),
                        command.getCode(),
                        command.getContactEmail(),
                        command.getContactPhone()
                ),
                command.getCreatedBy()
        );
    }
}
