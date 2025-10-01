package com.oynee.portfolio.partner.adapter.persistence.entity;

import com.oynee.portfolio.partner.domain.org.model.PartnerOrganization;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@Builder(access = PRIVATE)
@Getter
@Entity
@Table(name = "partner_org")
public class PartnerOrgEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partnerOrgId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Column
    private String contactEmail;

    @Column
    private String contactPhone;

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

    public static PartnerOrgEntity from(PartnerOrganization domain, String createdBy, String updatedBy) {
        return PartnerOrgEntity.builder()
                .name(domain.getName())
                .code(domain.getCode())
                .contactEmail(domain.getContactEmail())
                .contactPhone(domain.getContactPhone())
                .createdBy(createdBy)
                .updatedBy(updatedBy)
                .build();
    }

}