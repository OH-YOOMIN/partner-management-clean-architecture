package com.oynee.portfolio.partner.domain.partnership.dto;

import com.oynee.portfolio.partner.type.PartnershipStatusType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartnershipDto {
    
    private Long partnershipId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double commissionRate;
    private PartnershipStatusType status;

    // 조직 정보 요약 VO
    private PartnerOrgSummary organization;

    // 매장 정보 요약 VO
    private PartnerStoreSummary store;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PartnerOrgSummary {
        private Long orgId;
        private String name;
        private String code;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PartnerStoreSummary {
        private Long storeId;
        private String name;
        private String code;
    }
}
