package com.oynee.portfolio.partner.domain.store.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PartnerStoreDto {
    private Long partnerStoreId;
    private Long partnerOrgId;
    private String name;
    private String code;
    private String address;
    private String phone;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
}
