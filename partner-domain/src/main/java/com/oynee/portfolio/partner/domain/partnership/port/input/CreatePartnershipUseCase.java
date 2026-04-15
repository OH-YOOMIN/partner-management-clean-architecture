package com.oynee.portfolio.partner.domain.partnership.port.input;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

public interface CreatePartnershipUseCase {
    void createPartnership(Command command);

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    class Command {
        private Long partnerOrgId;
        private Long partnerStoreId;
        private LocalDate startDate;
        private LocalDate endDate;
        private Double commissionRate;
        private boolean activeFlag;
        private String createdBy;
    }
}
