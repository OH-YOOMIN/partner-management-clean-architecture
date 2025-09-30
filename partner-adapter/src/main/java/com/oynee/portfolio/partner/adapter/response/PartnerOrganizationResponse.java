package com.oynee.portfolio.partner.adapter.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@ApiModel("제휴 조직 응답")
public class PartnerOrganizationResponse {
    @ApiModelProperty("조직 ID")
    private Long partnerOrgId;

    @ApiModelProperty("조직명")
    private String name;

    @ApiModelProperty("조직 코드")
    private String code;

    @ApiModelProperty("담당자 이메일")
    private String contactEmail;

    @ApiModelProperty("담당자 전화번호")
    private String contactPhone;

    @ApiModelProperty("등록자 ID")
    private String createdBy;

    @ApiModelProperty("등록일시")
    private LocalDateTime createdAt;

    @ApiModelProperty("수정자 ID")
    private String updatedBy;

    @ApiModelProperty("수정일시")
    private LocalDateTime updatedAt;

}
