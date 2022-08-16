package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.repository.TicketRepository;
import br.com.meli.desafio_final.util.PaymentUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @Test
    void validateTicket() {
        BDDMockito.when(ticketRepository.save(PaymentUtils.newTickettoSave()))
                .thenReturn(PaymentUtils.newTickettoSave());
        assertThat(ticketService.validateTicket(PaymentUtils.newTickettoSave()));
    }

    @Test
    void validateTicket_Exception1() {
        try {
            ticketService.validateTicket(PaymentUtils.newTicket1toSave());
        } catch (BadRequest badRequest) {
            assertThat(badRequest.getMessage().equals("Código de barras inválido."));
        }
    }

    @Test
    void validateTicket_Exception2() {
        try {
            ticketService.validateTicket(PaymentUtils.newTicket2toSave());
        } catch (BadRequest badRequest) {
            assertThat(badRequest.getMessage().equals("Boleto com vencimento expirado."));
        }
    }

    @Test
    void save() {
        BDDMockito.when(ticketRepository.save(PaymentUtils.newTickettoSave()))
                .thenReturn(PaymentUtils.newTickettoSave());
        ticketService.save(PaymentUtils.newTickettoSave());
    }

}