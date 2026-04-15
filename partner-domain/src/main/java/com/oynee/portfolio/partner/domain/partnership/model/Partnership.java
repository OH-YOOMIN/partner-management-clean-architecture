package com.oynee.portfolio.partner.domain.partnership.model;

import com.oynee.portfolio.partner.type.PartnershipStatusType;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Partnership {
    private final Long partnerOrgId;
    private final Long storeId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Double commissionRate;
    private final PartnershipStatusType status;

    public Partnership(
            Long partnerOrgId,
            Long storeId,
            LocalDate startDate,
            LocalDate endDate,
            Double commissionRate,
            PartnershipStatusType status
    ) {
        if (partnerOrgId == null && storeId == null) {
            throw new IllegalArgumentException("제휴 대상은 조직 또는 매장 중 하나 이상이어야 합니다.");
        }
        if (startDate == null) {
            throw new IllegalArgumentException("제휴 시작일은 필수입니다.");
        }
        if (endDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("종료일은 시작일 이후여야 합니다.");
        }
        this.partnerOrgId = partnerOrgId;
        this.storeId = storeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.commissionRate = commissionRate;
        this.status = status;
    }
}
