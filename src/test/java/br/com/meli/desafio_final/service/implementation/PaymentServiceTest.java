package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.model.entity.Payment;
import br.com.meli.desafio_final.repository.PaymentRepository;
import br.com.meli.desafio_final.util.PaymentUtils;
import br.com.meli.desafio_final.util.PurchaseOrderUtils;
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
import java.util.regex.Matcher;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PixService pixService;

    @Mock
    private TicketService ticketService;

    @Mock
    private CredicardService credicardService;

    @Mock
    private PurchaseOrderService purchaseOrderService;

    @Mock
    private PaymentRepository paymentRepository;

    @Test
    void findAll() {
        BDDMockito.when(paymentRepository.findAll())
                .thenReturn(PaymentUtils.generatePaymentList());
        List<Payment> payments = paymentService.findAll();
        assertThat(payments.get(0).getFullPayment()).isEqualTo(7.9);
    }
    
    @Test
    void findPaymentByCredicard(){
        BDDMockito.when(paymentRepository.findPaymentByCredicard(2L))
                .thenReturn(ArgumentMatchers.anyList());
        paymentService.findPaymentByCredicard(2L);
    }

    @Test
    void paymenteByCredicard() {
        BDDMockito.when(purchaseOrderService.findById(2L))
                .thenReturn(PurchaseOrderUtils.newPurchase2ToSave());
        BDDMockito.when(paymentRepository.save(PaymentUtils.newPayment1ToSave()))
                .thenReturn(PaymentUtils.newPayment1ToSave());
        BDDMockito.when(credicardService.validateCredicard(PaymentUtils.newCredicard4ToSave()))
                .thenReturn(Boolean.TRUE);
        paymentService.paymenteByCredicard(PaymentUtils.newCredicard1ToSave(), 2L);

    }

    @Test
    void paymenteByCredicard_Exception() {
        BDDMockito.when(purchaseOrderService.findById(2L))
                .thenReturn(PurchaseOrderUtils.newPurchase3ToSave());
        try {
            paymentService.paymenteByCredicard(PaymentUtils.newCredicard1ToSave(), 2L);
        } catch (BadRequest badRequest) {
            assertThat(badRequest.getMessage()).isEqualTo("Carrinho já finalizado.");
        }
    }

    @Test
    void paymentByPix() {
        BDDMockito.when(purchaseOrderService.findById(2L))
                .thenReturn(PurchaseOrderUtils.newPurchase2ToSave());
        BDDMockito.when(paymentRepository.save(PaymentUtils.newPayment1ToSave()))
                .thenReturn(PaymentUtils.newPayment1ToSave());
        BDDMockito.when(pixService.validatePix(PaymentUtils.newPixtoSave()))
                .thenReturn(Boolean.TRUE);
        paymentService.paymentByPix(PaymentUtils.newPixtoSave(), 2L);
    }

    @Test
    void paymentByPix_Exception() {
        BDDMockito.when(purchaseOrderService.findById(2L))
                .thenReturn(PurchaseOrderUtils.newPurchase3ToSave());
        try {
            paymentService.paymentByPix(PaymentUtils.newPixtoSave(), 2L);
        } catch (BadRequest badRequest) {
            assertThat(badRequest.getMessage()).isEqualTo("Carrinho já finalizado.");
        }
    }

    @Test
    void paymentByTicket() {
        BDDMockito.when(purchaseOrderService.findById(2L))
                .thenReturn(PurchaseOrderUtils.newPurchase2ToSave());
        BDDMockito.when(paymentRepository.save(PaymentUtils.newPayment1ToSave()))
                .thenReturn(PaymentUtils.newPayment1ToSave());
        BDDMockito.when(ticketService.validateTicket(PaymentUtils.newTickettoSave()))
                .thenReturn(Boolean.TRUE);
        paymentService.paymentByTicket(PaymentUtils.newTickettoSave(), 2L);
    }

    @Test
    void paymentByTicket_Exception() {
        BDDMockito.when(purchaseOrderService.findById(2L))
                .thenReturn(PurchaseOrderUtils.newPurchase3ToSave());
        try {
            paymentService.paymentByTicket(PaymentUtils.newTickettoSave(), 2L);
        } catch (BadRequest badRequest) {
            assertThat(badRequest.getMessage()).isEqualTo("Carrinho já finalizado.");
        }
    }


}
