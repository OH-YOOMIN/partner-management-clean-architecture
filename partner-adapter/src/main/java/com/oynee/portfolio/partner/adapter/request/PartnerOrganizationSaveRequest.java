package com.oynee.portfolio.partner.adapter.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

import javax.validation.constraints.NotBlank;


@Builder(builderClassName = "POJOBuilder")
@Getter
@RequiredArgsConstructor
@ToString
@Schema(description = "제휴 조직 생성 요청")
@JsonDeserialize(builder = PartnerOrganizationSaveRequest.POJOBuilder.class)
public class PartnerOrganizationSaveRequest {

    @Schema(description = "조직명", requiredMode = RequiredMode.REQUIRED)
    @NotBlank
    private final String name;

    @Schema(description = "조직 코드", requiredMode = RequiredMode.REQUIRED)
    @NotBlank
    private final String code;

    @Schema(description = "담당자 이메일")
    private final String contactEmail;

    @Schema(description = "담당자 전화번호")
    private final String contactPhone;

    @Schema(description = "등록자 ID", requiredMode = RequiredMode.REQUIRED)
    @NotBlank
    private final String createdBy;

    @JsonPOJOBuilder(withPrefix = "")
    public static class POJOBuilder {}
}