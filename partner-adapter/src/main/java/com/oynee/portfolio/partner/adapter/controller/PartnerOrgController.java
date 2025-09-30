package com.oynee.portfolio.partner.adapter.controller;

import com.oynee.portfolio.partner.adapter.mapper.PartnerOrgMapper;
import com.oynee.portfolio.partner.adapter.request.PartnerOrganizationSaveRequest;
import com.oynee.portfolio.partner.adapter.response.PartnerOrganizationResponse;
import com.oynee.portfolio.partner.domain.org.port.input.RegisterPartnerOrganizationUseCase;
import com.oynee.portfolio.partner.domain.org.port.input.RetrievePartnerOrganizationUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = "제휴 조직")
public class PartnerOrgController {
    private final RetrievePartnerOrganizationUseCase retrievePartnerOrganizationUseCase;
    private final RegisterPartnerOrganizationUseCase registerPartnerOrganizationUseCase;

    private final PartnerOrgMapper partnerOrgMapper;

    @ApiOperation(value = "")
    @PostMapping("/organizations")
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

    @ApiOperation(value = "제휴 조직 단건 조회")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 404, message = "해당 조직을 찾을 수 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @GetMapping("/organizations/{orgId}")
    public ResponseEntity<PartnerOrganizationResponse> getOrganization(
            @PathVariable Long orgId
    ) {
        PartnerOrganizationResponse response = partnerOrgMapper.toResponse(retrievePartnerOrganizationUseCase.retrieve(orgId));
        return ResponseEntity.ok(response);
    }
}
