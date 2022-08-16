package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.model.dto.PaymentByCredicardDto;
import br.com.meli.desafio_final.model.entity.*;
import br.com.meli.desafio_final.model.enums.Status;
import br.com.meli.desafio_final.repository.PaymentRepository;
import br.com.meli.desafio_final.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private CredicardService credicardService;

    @Autowired
    private PixService pixService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Object> findPaymentByCredicard(Long id) {
        return paymentRepository.findPaymentByCredicard(id).stream().map(
                (obj) -> new PaymentByCredicardDto(obj[0], obj[1], obj[2], obj[3], obj[4])
        ).collect(Collectors.toList());
    }

    @Override
    public Payment paymenteByCredicard(Credicard credicard, Long idOrder) {
        PurchaseOrder purchaseOrderFound = purchaseOrderService.findById(idOrder);
        if (purchaseOrderFound.getStatus().equals(Status.OPEN)) {
            credicardService.validateCredicard(credicard);
            Payment newPayment = paymentRepository.save(Payment.builder().paymentDate(LocalDate.now())
                    .fullPayment(purchaseOrderFound.getTotalPrice()).purchaseOrder(purchaseOrderFound).build());
            credicard.setPayment(newPayment);
            credicardService.save(credicard);
            purchaseOrderService.updateToFinished(purchaseOrderFound.getId());
            return newPayment;
        }
        throw new BadRequest("Carrinho já finalizado.");
    }

    @Override
    public Payment paymentByPix(Pix pix, Long idOrder) {
        PurchaseOrder purchaseOrderFound = purchaseOrderService.findById(idOrder);
        if (purchaseOrderFound.getStatus().equals(Status.OPEN)) {
            pixService.validatePix(pix);
            Payment newPayment = paymentRepository.save(Payment.builder().paymentDate(LocalDate.now())
                    .fullPayment(purchaseOrderFound.getTotalPrice()).purchaseOrder(purchaseOrderFound).build());
            pix.setPayment(newPayment);
            pixService.save(pix);
            purchaseOrderService.updateToFinished(purchaseOrderFound.getId());
            return newPayment;

        }
        throw new BadRequest("Carrinho já finalizado.");
    }

    @Override
    public Payment paymentByTicket(Ticket ticket, Long idOrder) {
        PurchaseOrder purchaseOrderFound = purchaseOrderService.findById(idOrder);
        if (purchaseOrderFound.getStatus().equals(Status.OPEN)) {
            ticketService.validateTicket(ticket);
            Payment newPayment = paymentRepository.save(Payment.builder().paymentDate(LocalDate.now())
                    .fullPayment(purchaseOrderFound.getTotalPrice()).purchaseOrder(purchaseOrderFound).build());
            ticket.setPayment(newPayment);
            ticketService.save(ticket);
            purchaseOrderService.updateToFinished(purchaseOrderFound.getId());
            return newPayment;
        }
        throw new BadRequest("Carrinho já finalizado.");
    }

}
