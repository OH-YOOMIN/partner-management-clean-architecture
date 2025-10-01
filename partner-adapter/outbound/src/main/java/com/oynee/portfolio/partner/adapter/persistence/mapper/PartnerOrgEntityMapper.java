package com.oynee.portfolio.partner.adapter.persistence.mapper;

import com.oynee.portfolio.partner.domain.org.dto.PartnerOrganizationDto;
import com.oynee.portfolio.partner.adapter.persistence.entity.PartnerOrgEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PartnerOrgEntityMapper {
    PartnerOrganizationDto toDto(PartnerOrgEntity entity);
}
