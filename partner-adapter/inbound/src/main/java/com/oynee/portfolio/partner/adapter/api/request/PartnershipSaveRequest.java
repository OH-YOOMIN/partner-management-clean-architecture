package com.oynee.portfolio.partner.adapter.api.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder(builderClassName = "POJOBuilder")
@Getter
@RequiredArgsConstructor
@ToString
@Schema(description = "제휴 생성 요청")
@JsonDeserialize(builder = PartnershipSaveRequest.POJOBuilder.class)
public class PartnershipSaveRequest {

    @Schema(description = "브랜드 단위 제휴 시 조직 ID")
    private final Long partnerOrgId;

    @Schema(description = "매장 단위 제휴 시 매장 ID")
    private final Long partnerStoreId;

    @Schema(description = "제휴 시작일", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private final LocalDate startDate;

    @Schema(description = "제휴 종료일")
    private final LocalDate endDate;

    @Schema(description = "수수료율 (%)", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private final Double commissionRate;

    @Schema(description = "활성화 여부")
    private final boolean activeFlag;

    @Schema(description = "등록자 ID", requiredMode = RequiredMode.REQUIRED)
    @NotBlank
    private final String createdBy;

    @JsonPOJOBuilder(withPrefix = "")
    public static class POJOBuilder {}
}