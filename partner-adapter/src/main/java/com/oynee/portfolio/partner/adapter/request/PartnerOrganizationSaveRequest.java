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
@ApiModel("제휴 조직 생성 요청")
@JsonDeserialize(builder = PartnerOrganizationSaveRequest.POJOBuilder.class)
public class PartnerOrganizationSaveRequest {

    @ApiModelProperty(value = "조직명", required = true)
    private final String name;

    @ApiModelProperty(value = "조직 코드", required = true)
    private final String code;

    @ApiModelProperty("담당자 이메일")
    private final String contactEmail;

    @ApiModelProperty("담당자 전화번호")
    private final String contactPhone;

    @ApiModelProperty(value = "등록자 ID", required = true)
    private final String createdBy;

    @JsonPOJOBuilder(withPrefix = "")
    public static class POJOBuilder {}
}