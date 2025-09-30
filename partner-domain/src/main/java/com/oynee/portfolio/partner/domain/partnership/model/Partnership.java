package com.oynee.portfolio.partner.domain.partnership.model;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Partnership {
    private final Long headquartersId;
    private final Long storeId;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Partnership(Long headquartersId, Long storeId, LocalDate startDate, LocalDate endDate) {
        if (endDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("종료일은 시작일 이후여야 합니다.");
        }
        this.headquartersId = headquartersId;
        this.storeId = storeId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
