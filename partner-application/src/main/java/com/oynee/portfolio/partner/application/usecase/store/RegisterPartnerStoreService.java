package com.oynee.portfolio.partner.application.usecase.store;

import com.oynee.portfolio.partner.domain.store.model.PartnerStore;
import com.oynee.portfolio.partner.domain.store.port.input.RegisterPartnerStoreUseCase;
import com.oynee.portfolio.partner.domain.store.port.output.LoadPartnerStorePort;
import com.oynee.portfolio.partner.domain.store.port.output.SavePartnerStorePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterPartnerStoreService implements RegisterPartnerStoreUseCase {
    private final LoadPartnerStorePort loadPartnerStorePort;
    private final SavePartnerStorePort savePartnerStorePort;

    @Override
    public void registerStore(Command command) {
        if (loadPartnerStorePort.existsByCode(command.getCode())) {
            throw new IllegalStateException("이미 사용 중인 매장 코드입니다: " + command.getCode());
        }

        savePartnerStorePort.savePartnerStore(
                PartnerStore.create(
                        command.getPartnerOrgId(),
                        command.getName(),
                        command.getCode(),
                        command.getAddress(),
                        command.getPhone()
                ),
                command.getCreatedBy()
        );
    }
}
