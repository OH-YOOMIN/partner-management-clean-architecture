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

@Builder(builderClassName = "POJOBuilder")
@Getter
@RequiredArgsConstructor
@ToString
@Schema(description = "파트너 매장 생성 요청")
@JsonDeserialize(builder = PartnerStoreSaveRequest.POJOBuilder.class)
public class PartnerStoreSaveRequest {

    @Schema(description = "소속 조직 ID")
    private final Long partnerOrgId;

    @Schema(description = "매장명", requiredMode = RequiredMode.REQUIRED)
    @NotBlank
    private final String name;

    @Schema(description = "매장 코드", requiredMode = RequiredMode.REQUIRED)
    @NotBlank
    private final String code;

    @Schema(description = "주소")
    private final String address;

    @Schema(description = "전화번호")
    private final String phone;

    @Schema(description = "등록자 ID", requiredMode = RequiredMode.REQUIRED)
    @NotBlank
    private final String createdBy;

    @JsonPOJOBuilder(withPrefix = "")
    public static class POJOBuilder {}
}