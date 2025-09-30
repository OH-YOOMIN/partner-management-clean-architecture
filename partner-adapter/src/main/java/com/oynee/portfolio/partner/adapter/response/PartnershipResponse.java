package com.oynee.portfolio.partner.adapter.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Schema(description = "제휴 응답")
public class PartnershipResponse {

    @Schema(description = "제휴 ID")
    private Long partnershipId;

    @Schema(description = "제휴 시작일")
    private LocalDate startDate;

    @Schema(description = "제휴 종료일")
    private LocalDate endDate;

    @Schema(description = "수수료율")
    private Double commissionRate;

    @Schema(description = "제휴 활성 여부")
    private boolean active;

    @Schema(description = "조직 정보 요약")
    private PartnerOrgSummary organization;

    @Schema(description = "매장 정보 요약")
    private PartnerStoreSummary store;

    @Getter
    @Setter
    @ToString
    @Schema(description = "제휴 매장 요약 응답")
    public static class PartnerStoreSummary {

        @Schema(description = "매장 ID")
        private Long storeId;

        @Schema(description = "매장 이름")
        private String name;

        @Schema(description = "매장 코드")
        private String code;
    }

    @Getter
    @Setter
    @ToString
    @Schema(description = "제휴 조직 요약 응답")
    public static class PartnerOrgSummary {

        @Schema(description = "조직 ID")
        private Long orgId;

        @Schema(description = "조직 이름")
        private String name;

        @Schema(description = "조직 코드")
        private String code;
    }
}