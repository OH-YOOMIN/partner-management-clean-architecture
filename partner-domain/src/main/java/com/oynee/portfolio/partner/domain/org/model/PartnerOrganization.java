package com.oynee.portfolio.partner.domain.org.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class PartnerOrganization {

    private final String name;
    private final String code;
    private final String contactEmail;
    private final String contactPhone;

    public static PartnerOrganization create(
            String name,
            String code,
            String contactEmail,
            String contactPhone
    ) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("조직 코드는 필수입니다.");
        }

        String normalizedCode = code.toUpperCase(); // 정책: 조직 코드는 항상 대문자로 저장

        return PartnerOrganization.builder()
                .name(name)
                .code(normalizedCode)
                .contactEmail(contactEmail)
                .contactPhone(contactPhone)
                .build();
    }

}
