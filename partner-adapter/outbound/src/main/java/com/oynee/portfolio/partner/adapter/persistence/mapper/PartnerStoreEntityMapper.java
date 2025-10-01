package com.oynee.portfolio.partner.adapter.persistence.mapper;

import com.oynee.portfolio.partner.domain.store.dto.PartnerStoreDto;
import com.oynee.portfolio.partner.adapter.persistence.entity.PartnerStoreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PartnerStoreEntityMapper {
    PartnerStoreDto toDto(PartnerStoreEntity entity);
}
