package com.oynee.portfolio.partner.adapter.api.mapper;

import com.oynee.portfolio.partner.adapter.api.response.PartnerOrganizationResponse;
import com.oynee.portfolio.partner.domain.org.dto.PartnerOrganizationDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PartnerOrgMapper {
    PartnerOrganizationResponse toResponse(PartnerOrganizationDto dto);
}
