package com.oynee.portfolio.partner.adapter.controller;

import com.oynee.portfolio.partner.adapter.mapper.PartnershipMapper;
import com.oynee.portfolio.partner.adapter.response.PartnershipResponse;
import com.oynee.portfolio.partner.domain.partnership.port.input.RetrievePartnershipUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/partner", produces = APPLICATION_JSON_VALUE)
@Tag(name = "제휴", description = "제휴 관련 API")
public class PartnershipController {
    private final RetrievePartnershipUseCase retrievePartnershipUseCase;

    private final PartnershipMapper partnershipMapper;

    @GetMapping("/partnerships/{partnershipId}")
    @Operation(summary = "제휴 단건 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "해당 제휴를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<PartnershipResponse> getPartnership(
            @PathVariable Long partnershipId
    ) {
        PartnershipResponse response = partnershipMapper.toResponse(retrievePartnershipUseCase.retrieve(partnershipId));
        return ResponseEntity.ok(response);
    }
}
