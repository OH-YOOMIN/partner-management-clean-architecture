package com.oynee.portfolio.partner.domain.store.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class PartnerStore {
    private Long partnerStoreId;
    private Long partnerOrgId;
    private String name;
    private String code;
    private String address;
    private String phone;

    public static PartnerStore create(
            Long partnerOrgId,
            String name,
            String code,
            String address,
            String phone
    ) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("매장 코드는 필수입니다.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("매장 이름은 필수입니다.");
        }

        String normalizedCode = code.toUpperCase(); // 정책: 매장 코드는 항상 대문자

        return PartnerStore.builder()
                .partnerOrgId(partnerOrgId)
                .name(name)
                .code(normalizedCode)
                .address(address)
                .phone(phone)
                .build();
    }
}
