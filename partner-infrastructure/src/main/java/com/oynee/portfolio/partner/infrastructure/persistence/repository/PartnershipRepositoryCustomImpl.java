package com.oynee.portfolio.partner.infrastructure.persistence.repository;

import com.oynee.portfolio.partner.domain.partnership.dto.PartnershipDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.oynee.portfolio.partner.infrastructure.persistence.entity.QPartnerOrgEntity.partnerOrgEntity;
import static com.oynee.portfolio.partner.infrastructure.persistence.entity.QPartnerStoreEntity.partnerStoreEntity;
import static com.oynee.portfolio.partner.infrastructure.persistence.entity.QPartnershipEntity.partnershipEntity;

@Repository
@RequiredArgsConstructor
public class PartnershipRepositoryCustomImpl implements PartnershipRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<PartnershipDto> findPartnership(Long partnershipId) {
        PartnershipDto dto = queryFactory
                .select(Projections.constructor(
                        PartnershipDto.class,
                        partnershipEntity.partnershipId,
                        partnershipEntity.startDate,
                        partnershipEntity.endDate,
                        partnershipEntity.commissionRate,
                        partnershipEntity.status,
                        Projections.constructor(
                                PartnershipDto.PartnerOrgSummary.class,
                                partnerOrgEntity.partnerOrgId,
                                partnerOrgEntity.name,
                                partnerOrgEntity.code
                        ),
                        Projections.constructor(
                                PartnershipDto.PartnerStoreSummary.class,
                                partnerStoreEntity.partnerStoreId,
                                partnerStoreEntity.name,
                                partnerStoreEntity.code
                        )
                ))
                .from(partnershipEntity)
                .leftJoin(partnerOrgEntity)
                .on(partnershipEntity.partnerOrgId.eq(partnerOrgEntity.partnerOrgId))
                .leftJoin(partnerStoreEntity)
                .on(partnershipEntity.partnerStoreId.eq(partnerStoreEntity.partnerStoreId))
                .where(partnershipEntity.partnershipId.eq(partnershipId))
                .fetchOne();

        return Optional.ofNullable(dto);
    }
}
