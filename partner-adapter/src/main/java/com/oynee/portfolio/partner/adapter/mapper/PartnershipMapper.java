package com.oynee.portfolio.partner.adapter.mapper;

import com.oynee.portfolio.partner.adapter.response.PartnershipResponse;
import com.oynee.portfolio.partner.domain.partnership.dto.PartnershipDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PartnershipMapper {

    @Mapping(target = "active", expression = "java(dto.getStatus() != null && dto.getStatus().isActive())")
    PartnershipResponse toResponse(PartnershipDto dto);

    @AfterMapping
    default void handleNulls(@MappingTarget PartnershipResponse response) {
        if (response.getStore() != null && response.getStore().getStoreId() == null) {
            response.setStore(null);
        }
        if (response.getOrganization() != null && response.getOrganization().getOrgId() == null) {
            response.setOrganization(null);
        }
    }
}
