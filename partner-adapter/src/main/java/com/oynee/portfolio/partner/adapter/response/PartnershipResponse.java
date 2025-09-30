package com.oynee.portfolio.partner.adapter.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@ApiModel("제휴 응답")
public class PartnershipResponse {

    @ApiModelProperty(value = "제휴 ID")
    private Long partnershipId;

    @ApiModelProperty(value = "제휴 시작일")
    private LocalDate startDate;

    @ApiModelProperty(value = "제휴 종료일")
    private LocalDate endDate;

    @ApiModelProperty(value = "수수료율")
    private Double commissionRate;

    @ApiModelProperty(value = "제휴 상태")
    private boolean active;

    @ApiModelProperty(value = "조직 정보 요약")
    private PartnerOrgSummary organization;

    @ApiModelProperty(value = "매장 정보 요약")
    private PartnerStoreSummary store;

    @Getter
    @Setter
    @ToString
    @ApiModel("제휴 매장 요약 응답")
    public static class PartnerStoreSummary {

        @ApiModelProperty(value = "매장 ID")
        private Long storeId;

        @ApiModelProperty(value = "매장 이름")
        private String name;

        @ApiModelProperty(value = "매장 코드")
        private String code;
    }

    @Getter
    @Setter
    @ToString
    @ApiModel("제휴 조직 요약 응답")
    public static class PartnerOrgSummary {

        @ApiModelProperty(value = "조직 ID")
        private Long orgId;

        @ApiModelProperty(value = "조직 이름")
        private String name;

        @ApiModelProperty(value = "조직 코드")
        private String code;
    }
}
