package com.oynee.portfolio.partner.domain.org.port.input;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

public interface RegisterPartnerOrganizationUseCase {
    void registerOrganization(Command command);

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    class Command {
        private String name;
        private String code;
        private String contactEmail;
        private String contactPhone;
        private String createdBy;
    }
}
