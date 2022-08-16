package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.Credicard;
import br.com.meli.desafio_final.model.entity.Payment;
import br.com.meli.desafio_final.model.entity.Pix;
import br.com.meli.desafio_final.model.entity.Ticket;

import java.util.List;

public interface IPaymentService {

    Payment findyById(Long id);

    List<Payment> findAll();

    List<Object> findPaymentByCredicard(Long id);

    Payment paymenteByCredicard(Credicard credicard, Long idOrder);

    Payment paymentByPix(Pix pix, Long idOrder);

    Payment paymentByTicket(Ticket ticket, Long idOrder);

}
