package com.oynee.portfolio.partner.adapter.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Builder(builderClassName = "POJOBuilder")
@Getter
@RequiredArgsConstructor
@ToString
@ApiModel("파트너 매장 생성 요청")
@JsonDeserialize(builder = PartnerStoreSaveRequest.POJOBuilder.class)
public class PartnerStoreSaveRequest {
    @ApiModelProperty("소속 조직 ID")
    private final Long partnerOrgId;

    @ApiModelProperty(value = "매장명", required = true)
    private final String name;

    @ApiModelProperty(value = "매장 코드", required = true)
    private final String code;

    @ApiModelProperty("주소")
    private final String address;

    @ApiModelProperty("전화번호")
    private final String phone;

    @ApiModelProperty(value = "등록자 ID", required = true)
    private final String createdBy;

    @JsonPOJOBuilder(withPrefix = "")
    public static class POJOBuilder {}
}
