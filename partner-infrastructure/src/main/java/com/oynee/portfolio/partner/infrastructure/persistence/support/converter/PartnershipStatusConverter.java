package com.oynee.portfolio.partner.infrastructure.persistence.support.converter;

import com.oynee.portfolio.partner.type.PartnershipStatusType;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class PartnershipStatusConverter extends EnumConverter<PartnershipStatusType> {
    public PartnershipStatusConverter() {
        super(PartnershipStatusType.class);
    }
}
