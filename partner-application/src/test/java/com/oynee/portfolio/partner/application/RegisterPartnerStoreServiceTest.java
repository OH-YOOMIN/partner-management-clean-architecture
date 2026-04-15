package com.oynee.portfolio.partner.application;

import com.oynee.portfolio.partner.application.usecase.store.RegisterPartnerStoreService;
import com.oynee.portfolio.partner.domain.org.dto.PartnerOrganizationDto;
import com.oynee.portfolio.partner.domain.org.port.output.LoadOrganizationPort;
import com.oynee.portfolio.partner.domain.store.model.PartnerStore;
import com.oynee.portfolio.partner.domain.store.port.input.RegisterPartnerStoreUseCase.Command;
import com.oynee.portfolio.partner.domain.store.port.output.LoadPartnerStorePort;
import com.oynee.portfolio.partner.domain.store.port.output.SavePartnerStorePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterPartnerStoreServiceTest {

    @Mock
    private LoadPartnerStorePort loadPartnerStorePort;

    @Mock
    private SavePartnerStorePort savePartnerStorePort;

    @Mock
    private LoadOrganizationPort loadOrganizationPort;

    @InjectMocks
    private RegisterPartnerStoreService service;

    @Test
    void 조직이_존재하지않으면_예외발생() {
        Command command = new Command()
                .setPartnerOrgId(1L)
                .setName("테스트매장")
                .setCode("STORE001")
                .setAddress("서울시 강남구")
                .setPhone("02-1234-5678")
                .setCreatedBy("admin");

        when(loadOrganizationPort.loadOrganization(1L)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> service.registerStore(command));

        assertEquals("조직을 찾을 수 없습니다: 1", exception.getMessage());
        verify(savePartnerStorePort, never()).savePartnerStore(any(), any());
    }

    @Test
    void 매장코드가_이미존재하면_예외발생() {
        Command command = new Command()
                .setPartnerOrgId(1L)
                .setName("테스트매장")
                .setCode("STORE001")
                .setAddress("서울시 강남구")
                .setPhone("02-1234-5678")
                .setCreatedBy("admin");

        when(loadOrganizationPort.loadOrganization(1L)).thenReturn(Optional.of(new PartnerOrganizationDto()));
        when(loadPartnerStorePort.existsByCode("STORE001")).thenReturn(true);

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> service.registerStore(command));

        assertEquals("이미 사용 중인 매장 코드입니다: STORE001", exception.getMessage());
        verify(savePartnerStorePort, never()).savePartnerStore(any(), any());
    }

    @Test
    void 유효한_요청이면_매장저장_성공() {
        Command command = new Command()
                .setPartnerOrgId(1L)
                .setName("테스트매장")
                .setCode("store002")
                .setAddress("서울시 강남구")
                .setPhone("02-5678-1234")
                .setCreatedBy("admin");

        when(loadOrganizationPort.loadOrganization(1L)).thenReturn(Optional.of(new PartnerOrganizationDto()));
        when(loadPartnerStorePort.existsByCode("store002")).thenReturn(false);

        service.registerStore(command);

        ArgumentCaptor<PartnerStore> storeCaptor = ArgumentCaptor.forClass(PartnerStore.class);
        verify(savePartnerStorePort).savePartnerStore(storeCaptor.capture(), eq("admin"));

        PartnerStore savedStore = storeCaptor.getValue();
        assertEquals(1L, savedStore.getPartnerOrgId());
        assertEquals("테스트매장", savedStore.getName());
        assertEquals("STORE002", savedStore.getCode());
        assertEquals("서울시 강남구", savedStore.getAddress());
        assertEquals("02-5678-1234", savedStore.getPhone());
    }

    @Test
    void 조직ID가_null이면_조직조회없이_매장저장_성공() {
        Command command = new Command()
                .setName("테스트매장")
                .setCode("STORE003")
                .setAddress("서울시 강남구")
                .setPhone("02-1111-2222")
                .setCreatedBy("admin");

        when(loadPartnerStorePort.existsByCode("STORE003")).thenReturn(false);

        service.registerStore(command);

        verify(loadOrganizationPort, never()).loadOrganization(any());
        verify(savePartnerStorePort).savePartnerStore(any(), eq("admin"));
    }
}
