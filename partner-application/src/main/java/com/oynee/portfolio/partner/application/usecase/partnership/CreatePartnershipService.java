package com.oynee.portfolio.partner.application.usecase.partnership;

import com.oynee.portfolio.partner.domain.org.port.output.LoadOrganizationPort;
import com.oynee.portfolio.partner.domain.partnership.model.Partnership;
import com.oynee.portfolio.partner.domain.partnership.port.input.CreatePartnershipUseCase;
import com.oynee.portfolio.partner.domain.partnership.port.output.LoadPartnershipPort;
import com.oynee.portfolio.partner.domain.partnership.port.output.SavePartnershipPort;
import com.oynee.portfolio.partner.domain.store.port.output.LoadPartnerStorePort;
import com.oynee.portfolio.partner.type.PartnershipStatusType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class CreatePartnershipService implements CreatePartnershipUseCase {
    private final LoadPartnershipPort loadPartnershipPort;
    private final SavePartnershipPort savePartnershipPort;
    private final LoadOrganizationPort loadOrganizationPort;
    private final LoadPartnerStorePort loadPartnerStorePort;

    @Override
    public void createPartnership(Command command) {
        validateReferences(command);
        validateNoDuplicate(command);

        PartnershipStatusType status = command.isActiveFlag()
                ? PartnershipStatusType.ACTIVE
                : PartnershipStatusType.INACTIVE;

        savePartnershipPort.savePartnership(
                new Partnership(
                        command.getPartnerOrgId(),
                        command.getPartnerStoreId(),
                        command.getStartDate(),
                        command.getEndDate(),
                        command.getCommissionRate(),
                        status
                ),
                command.getCreatedBy()
        );
    }

    private void validateReferences(Command command) {
        if (command.getPartnerOrgId() != null
                && loadOrganizationPort.loadOrganization(command.getPartnerOrgId()).isEmpty()) {
            throw new NoSuchElementException("조직을 찾을 수 없습니다: " + command.getPartnerOrgId());
        }
        if (command.getPartnerStoreId() != null
                && loadPartnerStorePort.loadPartnerStore(command.getPartnerStoreId()).isEmpty()) {
            throw new NoSuchElementException("매장을 찾을 수 없습니다: " + command.getPartnerStoreId());
        }
    }

    private void validateNoDuplicate(Command command) {
        if (command.getPartnerStoreId() != null
                && loadPartnershipPort.existsByPartnerStoreId(command.getPartnerStoreId())) {
            throw new IllegalStateException("이미 제휴가 존재하는 매장입니다: " + command.getPartnerStoreId());
        }
        if (command.getPartnerStoreId() == null
                && command.getPartnerOrgId() != null
                && loadPartnershipPort.existsByPartnerOrgId(command.getPartnerOrgId())) {
            throw new IllegalStateException("이미 제휴가 존재하는 조직입니다: " + command.getPartnerOrgId());
        }
    }
}
