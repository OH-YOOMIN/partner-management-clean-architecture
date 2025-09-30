package com.oynee.portfolio.partner.type;

import com.oynee.portfolio.partner.common.type.EnumCodeAware;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 제휴 상태 타입
 */
@Getter
@AllArgsConstructor
public enum PartnershipStatusType implements EnumCodeAware {
    ACTIVE("ACTIVE", "활성화"),
    EXPIRED("EXPIRED", "만료"),
    INACTIVE("INACTIVE", "비활성화");

    private final String code;
    private final String title;

    /**
     * 활성 여부 반환
     */
    public boolean isActive() {
        return this == ACTIVE;
    }
}
