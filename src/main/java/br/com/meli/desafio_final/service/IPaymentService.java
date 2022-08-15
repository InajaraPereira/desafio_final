package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Credicard;
import br.com.meli.desafio_final.model.entity.Payment;
import br.com.meli.desafio_final.model.entity.Pix;
import br.com.meli.desafio_final.model.entity.Ticket;

import java.util.List;

public interface IPaymentService {

    List<Payment> findAll();

    Payment findBydId(Long id);

    Payment saveCredicard(Credicard credicard, Long idOrder);

    Payment savePix(Pix pix, Long idOrder);

    Payment saveTicket(Ticket ticket, Long idOrder);

}
