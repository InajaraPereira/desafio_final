package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.model.entity.*;

import java.util.List;

public interface IPaymentService {

    List<Payment> findAll();

    Payment findBydId(Long id);

    Payment paymenteByCredicard(Credicard credicard, Long idOrder);

    Payment paymentByPix(Pix pix, Long idOrder);

    Payment paymentByTicket(Ticket ticket, Long idOrder);

}
