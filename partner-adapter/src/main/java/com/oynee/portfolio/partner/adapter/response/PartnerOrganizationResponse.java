package com.oynee.portfolio.partner.adapter.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Schema(description = "제휴 조직 응답")
public class PartnerOrganizationResponse {
    @Schema(description = "조직 ID")
    private Long partnerOrgId;

    @Schema(description = "조직명")
    private String name;

    @Schema(description = "조직 코드")
    private String code;

    @Schema(description = "담당자 이메일")
    private String contactEmail;

    @Schema(description = "담당자 전화번호")
    private String contactPhone;

    @Schema(description = "등록자 ID")
    private String createdBy;

    @Schema(description = "등록일시")
    private LocalDateTime createdAt;

    @Schema(description = "수정자 ID")
    private String updatedBy;

    @Schema(description = "수정일시")
    private LocalDateTime updatedAt;

}
