package com.oynee.portfolio.partner.adapter.controller;

import com.oynee.portfolio.partner.adapter.mapper.PartnerStoreMapper;
import com.oynee.portfolio.partner.adapter.response.PartnerStoreResponse;
import com.oynee.portfolio.partner.domain.store.port.input.RetrievePartnerStoreUseCase;
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
@Api(tags = "제휴 매장")
public class PartnerStoreController {
    private final RetrievePartnerStoreUseCase  retrievePartnerStoreUseCase;

    private final PartnerStoreMapper partnerStoreMapper;

    @ApiOperation(value = "제휴 매장 단건 조회")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 404, message = "해당 매장을 찾을 수 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @GetMapping("/stores/{storeId}")
    public ResponseEntity<PartnerStoreResponse> getPartnerStore(
            @PathVariable Long storeId
    ) {
        PartnerStoreResponse response = partnerStoreMapper.toResponse(retrievePartnerStoreUseCase.retrieve(storeId));
        return ResponseEntity.ok(response);
    }
}
