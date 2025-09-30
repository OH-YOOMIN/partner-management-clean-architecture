package com.oynee.portfolio.partner.adapter.mapper;

import com.oynee.portfolio.partner.adapter.response.PartnerStoreResponse;
import com.oynee.portfolio.partner.domain.store.dto.PartnerStoreDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PartnerStoreMapper {
    PartnerStoreResponse toResponse(PartnerStoreDto dto);
}
