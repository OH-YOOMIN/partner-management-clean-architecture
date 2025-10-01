package com.oynee.portfolio.partner.adapter.api.controller;

import com.oynee.portfolio.partner.adapter.api.mapper.PartnerOrgMapper;
import com.oynee.portfolio.partner.adapter.api.request.PartnerOrganizationSaveRequest;
import com.oynee.portfolio.partner.adapter.api.response.PartnerOrganizationResponse;
import com.oynee.portfolio.partner.domain.org.port.input.RegisterPartnerOrganizationUseCase;
import com.oynee.portfolio.partner.domain.org.port.input.RetrievePartnerOrganizationUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/partner", produces = APPLICATION_JSON_VALUE)
@Tag(name = "제휴 조직", description = "제휴 조직 관련 API")
public class PartnerOrgController {
    private final RetrievePartnerOrganizationUseCase retrievePartnerOrganizationUseCase;
    private final RegisterPartnerOrganizationUseCase registerPartnerOrganizationUseCase;

    private final PartnerOrgMapper partnerOrgMapper;

    @PostMapping("/organizations")
    @Operation(summary = "제휴 조직 등록")
    public ResponseEntity<Void> saveOrganization(
            @Valid @RequestBody PartnerOrganizationSaveRequest request
    ) {
        RegisterPartnerOrganizationUseCase.Command command = new RegisterPartnerOrganizationUseCase.Command()
                .setName(request.getName())
                .setCode(request.getCode())
                .setContactEmail(request.getContactEmail())
                .setContactPhone(request.getContactPhone())
                .setCreatedBy(request.getCreatedBy());

        registerPartnerOrganizationUseCase.registerOrganization(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/organizations/{orgId}")
    @Operation(summary = "제휴 조직 단건 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "해당 조직을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<PartnerOrganizationResponse> getOrganization(
            @PathVariable Long orgId
    ) {
        PartnerOrganizationResponse response = partnerOrgMapper.toResponse(retrievePartnerOrganizationUseCase.retrieve(orgId));
        return ResponseEntity.ok(response);
    }
}
