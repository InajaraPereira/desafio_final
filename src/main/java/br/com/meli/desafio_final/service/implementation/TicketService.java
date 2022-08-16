package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.model.entity.Ticket;
import br.com.meli.desafio_final.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    /**
     * Este método valida data de vencimento e tamanho do numero do boleto.
     *
     * @param ticket
     * @return true
     */
    public boolean validateTicket(Ticket ticket) {
        if(ticket.getValidate().isAfter(LocalDate.now())){
            if (ticket.getNumber().length()>47){
                return true;
            }
            throw new BadRequest("Código de barras inválido.");
        }
        throw new BadRequest("Boleto com vencimento expirado.");
    }

    /**
     * Este método salva um boleto no banco.
     *
     * @param ticket
     * @return ticket
     */
    public Ticket save(Ticket ticket){
        return ticketRepository.save(ticket);
    }
}
