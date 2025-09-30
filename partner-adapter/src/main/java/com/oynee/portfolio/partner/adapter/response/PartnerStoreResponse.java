package com.oynee.portfolio.partner.adapter.response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@ApiModel("제휴 매장 응답")
public class PartnerStoreResponse {

    @ApiModelProperty(value = "매장 ID")
    private Long partnerStoreId;

    @ApiModelProperty(value = "소속 조직 ID (NULL이면 독립 매장)")
    private Long partnerOrgId;

    @ApiModelProperty(value = "매장 이름")
    private String name;

    @ApiModelProperty(value = "매장 코드 (Unique)")
    private String code;

    @ApiModelProperty(value = "매장 주소")
    private String address;

    @ApiModelProperty(value = "매장 전화번호")
    private String phone;

    @ApiModelProperty(value = "생성자")
    private String createdBy;

    @ApiModelProperty(value = "생성일시")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "수정자")
    private String updatedBy;

    @ApiModelProperty(value = "수정일시")
    private LocalDateTime updatedAt;
}