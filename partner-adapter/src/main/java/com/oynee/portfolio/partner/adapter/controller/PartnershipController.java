package com.oynee.portfolio.partner.adapter.controller;

import com.oynee.portfolio.partner.adapter.mapper.PartnershipMapper;
import com.oynee.portfolio.partner.adapter.response.PartnershipResponse;
import com.oynee.portfolio.partner.domain.partnership.port.input.RetrievePartnershipUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = "제휴")
public class PartnershipController {
    private final RetrievePartnershipUseCase retrievePartnershipUseCase;

    private final PartnershipMapper partnershipMapper;

    @ApiOperation(value = "제휴 단건 조회")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 404, message = "해당 제휴를 찾을 수 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @GetMapping("/partnerships/{partnershipId}")
    public ResponseEntity<PartnershipResponse> getPartnership(
            @PathVariable Long partnershipId
    ) {
        PartnershipResponse response = partnershipMapper.toResponse(retrievePartnershipUseCase.retrieve(partnershipId));
        return ResponseEntity.ok(response);
    }
}
