package com.oynee.portfoliio.partner.application;

import com.oynee.portfolio.partner.application.usecase.organization.RegisterPartnerOrganizationService;
import com.oynee.portfolio.partner.domain.org.model.PartnerOrganization;
import com.oynee.portfolio.partner.domain.org.port.input.RegisterPartnerOrganizationUseCase.Command;
import com.oynee.portfolio.partner.domain.org.port.output.LoadOrganizationPort;
import com.oynee.portfolio.partner.domain.org.port.output.SaveOrganizationPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterPartnerOrganizationServiceTest {

    @Mock
    private LoadOrganizationPort loadOrganizationPort;

    @Mock
    private SaveOrganizationPort saveOrganizationPort;

    @InjectMocks
    private RegisterPartnerOrganizationService service;

    @Test
    void 조직코드가_이미존재하면_예외발생() {
        Command command = new Command()
                .setName("테스트조직")
                .setCode("ORG001")
                .setContactEmail("test@example.com")
                .setContactPhone("010-1234-5678")
                .setCreatedBy("admin");

        when(loadOrganizationPort.existsByCode("ORG001")).thenReturn(true);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> service.registerOrganization(command));

        assertEquals("이미 사용 중인 조직 코드입니다: ORG001", exception.getReason());
        assertEquals(409, exception.getRawStatusCode());
        verify(saveOrganizationPort, never()).saveOrganization(any(), any());
    }

    @Test
    void 조직코드가_없으면_조직저장_성공() {
        Command command = new Command()
                .setName("테스트조직")
                .setCode("org002")
                .setContactEmail("test@example.com")
                .setContactPhone("010-5678-1234")
                .setCreatedBy("admin");

        when(loadOrganizationPort.existsByCode("org002")).thenReturn(false);

        service.registerOrganization(command);

        ArgumentCaptor<PartnerOrganization> orgCaptor = ArgumentCaptor.forClass(PartnerOrganization.class);
        verify(saveOrganizationPort).saveOrganization(orgCaptor.capture(), eq("admin"));

        PartnerOrganization savedOrg = orgCaptor.getValue();
        assertEquals("테스트조직", savedOrg.getName());
        assertEquals("ORG002", savedOrg.getCode());
        assertEquals("test@example.com", savedOrg.getContactEmail());
        assertEquals("010-5678-1234", savedOrg.getContactPhone());
    }
}
