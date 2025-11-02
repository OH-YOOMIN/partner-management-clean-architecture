package com.oynee.portfoliio.partner.application;

import com.oynee.portfolio.partner.application.usecase.organization.RetrievePartnerOrganizationService;
import com.oynee.portfolio.partner.domain.org.dto.PartnerOrganizationDto;
import com.oynee.portfolio.partner.domain.org.port.output.LoadOrganizationPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetrievePartnerOrganizationServiceTest {

    @Mock
    private LoadOrganizationPort loadOrganizationPort;

    @InjectMocks
    private RetrievePartnerOrganizationService service;

    @Test
    void 조직이_존재하면_정상적으로_조회된다() {
        Long orgId = 1L;
        PartnerOrganizationDto dto = new PartnerOrganizationDto();
        dto.setPartnerOrgId(orgId);
        dto.setName("테스트조직");
        dto.setCode("ORG001");
        dto.setContactEmail("test@example.com");
        dto.setContactPhone("010-1234-5678");
        dto.setCreatedBy("admin");
        dto.setCreatedAt(LocalDateTime.now());

        when(loadOrganizationPort.loadOrganization(orgId)).thenReturn(Optional.of(dto));

        PartnerOrganizationDto result = service.retrieve(orgId);

        assertNotNull(result);
        assertEquals("테스트조직", result.getName());
        assertEquals("ORG001", result.getCode());
        verify(loadOrganizationPort, times(1)).loadOrganization(orgId);
    }

    @Test
    void 조직이_존재하지_않으면_NOT_FOUND_예외발생() {
        Long orgId = 2L;
        when(loadOrganizationPort.loadOrganization(orgId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> service.retrieve(orgId));

        assertEquals(404, exception.getRawStatusCode());
        verify(loadOrganizationPort, times(1)).loadOrganization(orgId);
    }
}
