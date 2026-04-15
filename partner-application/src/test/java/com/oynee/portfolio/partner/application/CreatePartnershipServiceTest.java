package com.oynee.portfolio.partner.application;

import com.oynee.portfolio.partner.application.usecase.partnership.CreatePartnershipService;
import com.oynee.portfolio.partner.domain.org.dto.PartnerOrganizationDto;
import com.oynee.portfolio.partner.domain.org.port.output.LoadOrganizationPort;
import com.oynee.portfolio.partner.domain.partnership.model.Partnership;
import com.oynee.portfolio.partner.domain.partnership.port.input.CreatePartnershipUseCase.Command;
import com.oynee.portfolio.partner.domain.partnership.port.output.LoadPartnershipPort;
import com.oynee.portfolio.partner.domain.partnership.port.output.SavePartnershipPort;
import com.oynee.portfolio.partner.domain.store.dto.PartnerStoreDto;
import com.oynee.portfolio.partner.domain.store.port.output.LoadPartnerStorePort;
import com.oynee.portfolio.partner.type.PartnershipStatusType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreatePartnershipServiceTest {

    @Mock
    private LoadPartnershipPort loadPartnershipPort;

    @Mock
    private SavePartnershipPort savePartnershipPort;

    @Mock
    private LoadOrganizationPort loadOrganizationPort;

    @Mock
    private LoadPartnerStorePort loadPartnerStorePort;

    @InjectMocks
    private CreatePartnershipService service;

    @Test
    void 조직이_존재하지않으면_예외발생() {
        Command command = new Command()
                .setPartnerOrgId(1L)
                .setStartDate(LocalDate.of(2026, 1, 1))
                .setActiveFlag(true)
                .setCreatedBy("admin");

        when(loadOrganizationPort.loadOrganization(1L)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> service.createPartnership(command));

        assertEquals("조직을 찾을 수 없습니다: 1", exception.getMessage());
        verify(savePartnershipPort, never()).savePartnership(any(), any());
    }

    @Test
    void 매장이_존재하지않으면_예외발생() {
        Command command = new Command()
                .setPartnerStoreId(10L)
                .setStartDate(LocalDate.of(2026, 1, 1))
                .setActiveFlag(true)
                .setCreatedBy("admin");

        when(loadPartnerStorePort.loadPartnerStore(10L)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> service.createPartnership(command));

        assertEquals("매장을 찾을 수 없습니다: 10", exception.getMessage());
        verify(savePartnershipPort, never()).savePartnership(any(), any());
    }

    @Test
    void 매장에_이미제휴가_존재하면_예외발생() {
        Command command = new Command()
                .setPartnerOrgId(1L)
                .setPartnerStoreId(10L)
                .setStartDate(LocalDate.of(2026, 1, 1))
                .setActiveFlag(true)
                .setCreatedBy("admin");

        when(loadOrganizationPort.loadOrganization(1L)).thenReturn(Optional.of(new PartnerOrganizationDto()));
        when(loadPartnerStorePort.loadPartnerStore(10L)).thenReturn(Optional.of(new PartnerStoreDto()));
        when(loadPartnershipPort.existsByPartnerStoreId(10L)).thenReturn(true);

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> service.createPartnership(command));

        assertEquals("이미 제휴가 존재하는 매장입니다: 10", exception.getMessage());
        verify(savePartnershipPort, never()).savePartnership(any(), any());
    }

    @Test
    void 조직에_이미제휴가_존재하면_예외발생() {
        Command command = new Command()
                .setPartnerOrgId(1L)
                .setStartDate(LocalDate.of(2026, 1, 1))
                .setActiveFlag(true)
                .setCreatedBy("admin");

        when(loadOrganizationPort.loadOrganization(1L)).thenReturn(Optional.of(new PartnerOrganizationDto()));
        when(loadPartnershipPort.existsByPartnerOrgId(1L)).thenReturn(true);

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> service.createPartnership(command));

        assertEquals("이미 제휴가 존재하는 조직입니다: 1", exception.getMessage());
        verify(savePartnershipPort, never()).savePartnership(any(), any());
    }

    @Test
    void 유효한_요청이면_제휴저장_성공() {
        Command command = new Command()
                .setPartnerOrgId(1L)
                .setPartnerStoreId(10L)
                .setStartDate(LocalDate.of(2026, 1, 1))
                .setEndDate(LocalDate.of(2026, 12, 31))
                .setCommissionRate(0.15)
                .setActiveFlag(true)
                .setCreatedBy("admin");

        when(loadOrganizationPort.loadOrganization(1L)).thenReturn(Optional.of(new PartnerOrganizationDto()));
        when(loadPartnerStorePort.loadPartnerStore(10L)).thenReturn(Optional.of(new PartnerStoreDto()));
        when(loadPartnershipPort.existsByPartnerStoreId(10L)).thenReturn(false);

        service.createPartnership(command);

        ArgumentCaptor<Partnership> partnershipCaptor = ArgumentCaptor.forClass(Partnership.class);
        verify(savePartnershipPort).savePartnership(partnershipCaptor.capture(), eq("admin"));

        Partnership savedPartnership = partnershipCaptor.getValue();
        assertEquals(1L, savedPartnership.getPartnerOrgId());
        assertEquals(10L, savedPartnership.getStoreId());
        assertEquals(LocalDate.of(2026, 1, 1), savedPartnership.getStartDate());
        assertEquals(LocalDate.of(2026, 12, 31), savedPartnership.getEndDate());
        assertEquals(0.15, savedPartnership.getCommissionRate());
        assertEquals(PartnershipStatusType.ACTIVE, savedPartnership.getStatus());
    }

    @Test
    void activeFlag가_false면_INACTIVE상태로_저장() {
        Command command = new Command()
                .setPartnerOrgId(1L)
                .setStartDate(LocalDate.of(2026, 1, 1))
                .setActiveFlag(false)
                .setCreatedBy("admin");

        when(loadOrganizationPort.loadOrganization(1L)).thenReturn(Optional.of(new PartnerOrganizationDto()));
        when(loadPartnershipPort.existsByPartnerOrgId(1L)).thenReturn(false);

        service.createPartnership(command);

        ArgumentCaptor<Partnership> partnershipCaptor = ArgumentCaptor.forClass(Partnership.class);
        verify(savePartnershipPort).savePartnership(partnershipCaptor.capture(), eq("admin"));

        assertEquals(PartnershipStatusType.INACTIVE, partnershipCaptor.getValue().getStatus());
    }
}
