package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.model.dto.InBoundOrderDto;
import br.com.meli.desafio_final.model.entity.*;
import br.com.meli.desafio_final.model.service.implementation.*;
import br.com.meli.desafio_final.model.repository.InboundOrderRepository;
import br.com.meli.desafio_final.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class InBoundOrderServiceTeste {

    @InjectMocks
    private InBoundOrderService inBoundOrderService;

    @Mock
    private InboundOrderRepository inboundOrderRepository;

    @Mock
    private ValidationService validationService;

    @Mock
    private AdsenseService adsenseService;

    @Mock
    private BatchService batchService;

    @Mock
    private SectionService sectionService;

    @Test
    public void createInboundOrder() {
        Agent agent = AgentUtils.newAgent();
        Section sectionFresh = SectionUtils.newSectionFresh();
        Adsense adsense = AdsenseUtils.newAdsense1ToSave();
        Batch batch = BatchUtils.newBatch1ToSave();
        InBoundOrder inBoundOrder = InboundOrderUtils.newInboundOrder();
        BDDMockito.when(validationService.validateAgent(ArgumentMatchers.any(Long.class)))
                .thenReturn(agent);
        BDDMockito.when(validationService.validateSection(ArgumentMatchers.any(Section.class)))
                .thenReturn(sectionFresh);
        BDDMockito.when(adsenseService.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(adsense);
        BDDMockito.willDoNothing().given(validationService).validateSeller(adsense.getSeller());
        BDDMockito.willDoNothing().given(validationService).validateProduct(adsense.getProduct());
        BDDMockito.willDoNothing().given(sectionService).setAndUpdateCapacity(1500D, sectionFresh);
        BDDMockito.when(inboundOrderRepository.save(ArgumentMatchers.any(InBoundOrder.class)))
                .thenReturn(inBoundOrder);
        BDDMockito.when(batchService.saveBatch(ArgumentMatchers.any(Batch.class)))
                .thenReturn(batch);
        List<InBoundOrderDto> inbourdOrderCreated = inBoundOrderService.create(InboundOrderUtils.inBoundOrderToCreated(), 1L); // ação

        assertThat(inbourdOrderCreated).isNotNull();
        assertThat(inbourdOrderCreated.size()).isEqualTo(1);

    }

    @Test
    public void updateInboundOrderWhenQuantityIsLess() {
        Agent agent = AgentUtils.newAgent();
        Section sectionFresh = SectionUtils.newSectionFresh();
        Adsense adsense = AdsenseUtils.newAdsense1ToSave();
        Batch batch = BatchUtils.newBatch1ToSave();
        InBoundOrder inBoundOrder = InboundOrderUtils.newInboundOrder();
        InBoundOrder inBoundOrderToUpdate = InboundOrderUtils.inBoundOrderToCreated();
        inBoundOrderToUpdate.setId(1L);
        inBoundOrderToUpdate.getBatchStock().get(0).setCurrentQuantity(1);
        BDDMockito.when(inboundOrderRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(inBoundOrderToUpdate));
        BDDMockito.when(validationService.validateAgent(ArgumentMatchers.any(Long.class)))
                .thenReturn(agent);
        BDDMockito.when(validationService.validateSection(ArgumentMatchers.any(Section.class)))
                .thenReturn(sectionFresh);
        BDDMockito.when(adsenseService.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(adsense);
        BDDMockito.willDoNothing().given(validationService).validateSeller(adsense.getSeller());
        BDDMockito.willDoNothing().given(validationService).validateProduct(adsense.getProduct());
        BDDMockito.willDoNothing().given(sectionService).setAndUpdateCapacity(-1485D, sectionFresh);
        BDDMockito.when(batchService.findByBatchNumberAndInboundOrderId(ArgumentMatchers.any(Long.class), ArgumentMatchers.any(Long.class)))
                .thenReturn(batch);
        BDDMockito.when(inboundOrderRepository.save(ArgumentMatchers.any(InBoundOrder.class)))
                .thenReturn(inBoundOrder);
        BDDMockito.when(batchService.saveBatch(ArgumentMatchers.any(Batch.class)))
                .thenReturn(batch);
        List<InBoundOrderDto> inbourdOrderCreated = inBoundOrderService.update(inBoundOrderToUpdate, 1L); // ação

        assertThat(inbourdOrderCreated).isNotNull();
        assertThat(inbourdOrderCreated.size()).isEqualTo(1);

    }

    @Test
    public void updateInboundOrderWhenQuantityisBigger() {
        Agent agent = AgentUtils.newAgent();
        Section sectionFresh = SectionUtils.newSectionFresh();
        Adsense adsense = AdsenseUtils.newAdsense1ToSave();
        Batch batch = BatchUtils.newBatch1ToSave();
        InBoundOrder inBoundOrder = InboundOrderUtils.newInboundOrder();
        InBoundOrder inBoundOrderToUpdate = InboundOrderUtils.inBoundOrderToCreated();
        inBoundOrderToUpdate.setId(1L);
        inBoundOrderToUpdate.getBatchStock().get(0).setCurrentQuantity(110);
        BDDMockito.when(inboundOrderRepository.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(inBoundOrderToUpdate));
        BDDMockito.when(validationService.validateAgent(ArgumentMatchers.any(Long.class)))
                .thenReturn(agent);
        BDDMockito.when(validationService.validateSection(ArgumentMatchers.any(Section.class)))
                .thenReturn(sectionFresh);
        BDDMockito.when(adsenseService.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(adsense);
        BDDMockito.willDoNothing().given(validationService).validateSeller(adsense.getSeller());
        BDDMockito.willDoNothing().given(validationService).validateProduct(adsense.getProduct());
        BDDMockito.willDoNothing().given(sectionService).setAndUpdateCapacity(150D, sectionFresh);
        BDDMockito.when(batchService.findByBatchNumberAndInboundOrderId(ArgumentMatchers.any(Long.class), ArgumentMatchers.any(Long.class)))
                .thenReturn(batch);
        BDDMockito.when(inboundOrderRepository.save(ArgumentMatchers.any(InBoundOrder.class)))
                .thenReturn(inBoundOrder);
        BDDMockito.when(batchService.saveBatch(ArgumentMatchers.any(Batch.class)))
                .thenReturn(batch);
        List<InBoundOrderDto> inbourdOrderCreated = inBoundOrderService.update(inBoundOrderToUpdate, 1L); // ação

        assertThat(inbourdOrderCreated).isNotNull();
        assertThat(inbourdOrderCreated.size()).isEqualTo(1);

    }
}
