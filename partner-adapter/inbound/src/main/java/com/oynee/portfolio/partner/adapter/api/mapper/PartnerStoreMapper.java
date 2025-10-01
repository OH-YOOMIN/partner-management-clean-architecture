package com.oynee.portfolio.partner.adapter.api.mapper;

import com.oynee.portfolio.partner.adapter.api.response.PartnerStoreResponse;
import com.oynee.portfolio.partner.domain.store.dto.PartnerStoreDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PartnerStoreMapper {
    PartnerStoreResponse toResponse(PartnerStoreDto dto);
}
