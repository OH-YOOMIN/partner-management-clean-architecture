package com.oynee.portfolio.partner.adapter.api.controller;

import com.oynee.portfolio.partner.adapter.api.mapper.PartnerStoreMapper;
import com.oynee.portfolio.partner.adapter.api.response.PartnerStoreResponse;
import com.oynee.portfolio.partner.domain.store.port.input.RetrievePartnerStoreUseCase;
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
@Tag(name = "제휴 매장", description = "제휴 매장 관련 API")
public class PartnerStoreController {
    private final RetrievePartnerStoreUseCase  retrievePartnerStoreUseCase;

    private final PartnerStoreMapper partnerStoreMapper;

    @Operation(summary = "제휴 매장 단건 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "해당 매장을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/stores/{storeId}")
    public ResponseEntity<PartnerStoreResponse> getPartnerStore(
            @PathVariable Long storeId
    ) {
        PartnerStoreResponse response = partnerStoreMapper.toResponse(retrievePartnerStoreUseCase.retrieve(storeId));
        return ResponseEntity.ok(response);
    }
}
