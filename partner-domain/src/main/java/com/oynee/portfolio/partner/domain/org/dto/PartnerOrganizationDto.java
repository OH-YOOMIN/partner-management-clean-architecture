package com.oynee.portfolio.partner.domain.org.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PartnerOrganizationDto {
    private Long partnerOrgId;
    private String name;
    private String code;
    private String contactEmail;
    private String contactPhone;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
}
