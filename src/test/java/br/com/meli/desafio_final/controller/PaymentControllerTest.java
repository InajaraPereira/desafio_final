package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.model.entity.Payment;
import br.com.meli.desafio_final.service.implementation.PaymentService;
import br.com.meli.desafio_final.util.PaymentUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @Test
    void findAll() {
        BDDMockito.when(paymentService.findAll()).thenReturn(PaymentUtils.generatePaymentList());
        ResponseEntity<List<Payment>> response = paymentController.findAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isPositive().isEqualTo(1);
        assertThat(response.getBody().get(0).getFullPayment()).isEqualTo(7.90);
    }

    @Test
    void findPaymentByCredicard() {
        BDDMockito.when(paymentService.findPaymentByCredicard(1L))
                .thenReturn(ArgumentMatchers.anyObject());
        ResponseEntity<Object> response = paymentController.findPaymentByCredicard(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void paymenteByCredicard() {
        BDDMockito.when(paymentService.paymenteByCredicard(PaymentUtils.newCredicard1ToSave(), 1L))
                .thenReturn(PaymentUtils.newPayment1ToSave());
        ResponseEntity<Payment> response =
                paymentController.paymenteByCredicard(PaymentUtils.newCredicard1ToSave(), 1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void paymentByPix() {
        BDDMockito.when(paymentService.paymentByPix(PaymentUtils.newPixtoSave(), 1L))
                .thenReturn(PaymentUtils.newPayment1ToSave());
        ResponseEntity<Payment> response =
                paymentController.paymentByPix(PaymentUtils.newPixtoSave(), 1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void paymentByTicket() {
        BDDMockito.when(paymentService.paymentByTicket(PaymentUtils.newTicket1toSave(), 1L))
                .thenReturn(PaymentUtils.newPayment1ToSave());
        ResponseEntity<Payment> response =
                paymentController.paymentByTicket(PaymentUtils.newTicket2toSave(), 1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}