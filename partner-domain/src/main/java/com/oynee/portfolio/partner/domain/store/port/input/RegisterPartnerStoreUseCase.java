package com.oynee.portfolio.partner.domain.store.port.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

public interface RegisterPartnerStoreUseCase {
    void registerStore(Command command);

    @Getter
    @NoArgsConstructor
    @Accessors(chain = true)
    class Command {
        private Long organizationId;
        private String storeName;
        private String address;
    }
}
