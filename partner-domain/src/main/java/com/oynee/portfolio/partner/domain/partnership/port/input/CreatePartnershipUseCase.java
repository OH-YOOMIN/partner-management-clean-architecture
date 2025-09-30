package com.oynee.portfolio.partner.domain.partnership.port.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

public interface CreatePartnershipUseCase {
    void createPartnership(Command command);

    @Getter
    @NoArgsConstructor
    @Accessors(chain = true)
    class Command {
        private Long organizationId;
        private Long storeId;
        private String agreementTerms;
    }
}
