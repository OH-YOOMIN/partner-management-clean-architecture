package com.oynee.portfolio.partner.domain.store.port.input;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

public interface RegisterPartnerStoreUseCase {
    void registerStore(Command command);

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    class Command {
        private Long partnerOrgId;
        private String name;
        private String code;
        private String address;
        private String phone;
        private String createdBy;
    }
}
