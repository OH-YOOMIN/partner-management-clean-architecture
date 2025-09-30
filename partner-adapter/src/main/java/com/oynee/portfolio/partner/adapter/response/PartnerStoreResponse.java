package com.oynee.portfolio.partner.adapter.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Schema(description = "제휴 매장 응답")
public class PartnerStoreResponse {

    @Schema(description = "매장 ID")
    private Long partnerStoreId;

    @Schema(description = "소속 조직 ID (NULL이면 독립 매장)")
    private Long partnerOrgId;

    @Schema(description = "매장 이름")
    private String name;

    @Schema(description = "매장 코드 (Unique)")
    private String code;

    @Schema(description = "매장 주소")
    private String address;

    @Schema(description = "매장 전화번호")
    private String phone;

    @Schema(description = "생성자")
    private String createdBy;

    @Schema(description = "생성일시")
    private LocalDateTime createdAt;

    @Schema(description = "수정자")
    private String updatedBy;

    @Schema(description = "수정일시")
    private LocalDateTime updatedAt;
}