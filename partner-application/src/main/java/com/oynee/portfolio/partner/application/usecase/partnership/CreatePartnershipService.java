package com.oynee.portfolio.partner.application.usecase.partnership;

import com.oynee.portfolio.partner.domain.partnership.model.Partnership;
import com.oynee.portfolio.partner.domain.partnership.port.input.CreatePartnershipUseCase;
import com.oynee.portfolio.partner.domain.partnership.port.output.LoadPartnershipPort;
import com.oynee.portfolio.partner.domain.partnership.port.output.SavePartnershipPort;
import com.oynee.portfolio.partner.type.PartnershipStatusType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreatePartnershipService implements CreatePartnershipUseCase {
    private final LoadPartnershipPort loadPartnershipPort;
    private final SavePartnershipPort savePartnershipPort;

    @Override
    public void createPartnership(Command command) {
        if (command.getPartnerStoreId() != null
                && loadPartnershipPort.existsByPartnerStoreId(command.getPartnerStoreId())) {
            throw new IllegalStateException("이미 제휴가 존재하는 매장입니다: " + command.getPartnerStoreId());
        }
        if (command.getPartnerStoreId() == null
                && command.getPartnerOrgId() != null
                && loadPartnershipPort.existsByPartnerOrgId(command.getPartnerOrgId())) {
            throw new IllegalStateException("이미 제휴가 존재하는 조직입니다: " + command.getPartnerOrgId());
        }

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
}
