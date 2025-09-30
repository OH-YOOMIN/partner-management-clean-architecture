package com.oynee.portfolio.partner.adapter.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Builder(builderClassName = "POJOBuilder")
@Getter
@RequiredArgsConstructor
@ToString
@ApiModel("제휴 생성 요청")
@JsonDeserialize(builder = PartnershipSaveRequest.POJOBuilder.class)
public class PartnershipSaveRequest {

    @ApiModelProperty("브랜드 단위 제휴 시 조직 ID")
    private final Long partnerOrgId;

    @ApiModelProperty("매장 단위 제휴 시 매장 ID")
    private final Long partnerStoreId;

    @ApiModelProperty(value = "제휴 시작일", required = true)
    private final LocalDate startDate;

    @ApiModelProperty("제휴 종료일")
    private final LocalDate endDate;

    @ApiModelProperty(value = "수수료율 (%)", required = true)
    private final Double commissionRate;

    @ApiModelProperty(value = "활성화 여부")
    private final boolean activeFlag;

    @ApiModelProperty(value = "등록자 ID", required = true)
    private final String createdBy;

    @JsonPOJOBuilder(withPrefix = "")
    public static class POJOBuilder {}
}
