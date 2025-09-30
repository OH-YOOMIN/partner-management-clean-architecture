package com.oynee.portfolio.partner.domain.org.port.output;

import com.oynee.portfolio.partner.domain.org.model.PartnerOrganization;

public interface SaveOrganizationPort {
    void saveOrganization(PartnerOrganization partnerOrganization, String createdBy);
}
