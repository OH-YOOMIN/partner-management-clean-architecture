package com.oynee.portfolio.partner.adapter.persistence.entity;

import com.oynee.portfolio.partner.domain.partnership.model.Partnership;
import com.oynee.portfolio.partner.type.PartnershipStatusType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@Builder(access = PRIVATE)
@Getter
@Entity
@Table(name = "partnership")
public class PartnershipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partnershipId;

    @Column
    private Long partnerOrgId;

    @Column
    private Long partnerStoreId;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @Column(nullable = false)
    private Double commissionRate;

    @Column
    private PartnershipStatusType status;

    @Column(nullable = false)
    private String createdBy;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String updatedBy;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public static PartnershipEntity from(Partnership domain, String createdBy, String updatedBy) {
        return PartnershipEntity.builder()
                .partnerOrgId(domain.getPartnerOrgId())
                .partnerStoreId(domain.getStoreId())
                .startDate(domain.getStartDate())
                .endDate(domain.getEndDate())
                .commissionRate(domain.getCommissionRate())
                .status(domain.getStatus())
                .createdBy(createdBy)
                .updatedBy(updatedBy)
                .build();
    }
}
