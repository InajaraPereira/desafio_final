package br.com.meli.desafio_final.service.implementation;

import br.com.meli.desafio_final.exception.BadRequest;
import br.com.meli.desafio_final.exception.NotFound;
import br.com.meli.desafio_final.model.entity.*;
import br.com.meli.desafio_final.model.enums.PaymentType;
import br.com.meli.desafio_final.model.enums.Status;
import br.com.meli.desafio_final.repository.PaymentRepository;
import br.com.meli.desafio_final.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    public Payment findBydId(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> {
            throw new NotFound("Pagamento inexistente");
        });
    }

    @Override
    public Payment saveCredicard(Credicard credicard, Long idOrder) {
        PurchaseOrder purchaseOrderFound = purchaseOrderService.findById(idOrder);
        if (credicardService.validateCredicard(credicard)) {
            if (purchaseOrderFound.getStatus().equals(Status.OPEN)) {
                purchaseOrderService.updateToFinished(purchaseOrderFound.getId());
                return paymentRepository.save(Payment.builder()
                        .paymentType(PaymentType.CREDICARD)
                        .paymentDate(LocalDateTime.now())
                        .fullPayment(purchaseOrderFound.getTotalPrice())
                        .purchaseOrder(purchaseOrderFound)
                        .build());
            }
            throw new BadRequest("Carrinho já finalizado.");
        }
        throw new BadRequest("Pagamento não finalizado!");
    }

    @Override
    public Payment savePix(Pix pix, Long idOrder) {
        PurchaseOrder purchaseOrderFound = purchaseOrderService.findById(idOrder);
        if (pixService.validatePix(pix)) {
            if (purchaseOrderFound.getStatus().equals(Status.OPEN)) {
                purchaseOrderService.updateToFinished(purchaseOrderFound.getId());
                return paymentRepository.save(Payment.builder()
                        .paymentType(PaymentType.PIX)
                        .paymentDate(LocalDateTime.now())
                        .fullPayment(purchaseOrderFound.getTotalPrice())
                        .purchaseOrder(purchaseOrderFound)
                        .build());
            }
            throw new BadRequest("Carrinho já finalizado");
        }
        throw new BadRequest("Pagamento não finalizado!");
    }

    @Override
    public Payment saveTicket(Ticket ticket, Long idOrder) {
        PurchaseOrder purchaseOrderFound = purchaseOrderService.findById(idOrder);
        if (ticketService.validateTicket(ticket)) {
            if (purchaseOrderFound.getStatus().equals(Status.OPEN)) {
                purchaseOrderService.updateToFinished(purchaseOrderFound.getId());
                return paymentRepository.save(Payment.builder()
                        .paymentType(PaymentType.TICKET)
                        .paymentDate(LocalDateTime.now())
                        .fullPayment(purchaseOrderFound.getTotalPrice())
                        .purchaseOrder(purchaseOrderFound)
                        .build());
            }
            throw new BadRequest("Carrinho já finalizado");
        }
        throw new BadRequest("Pagamento não finalizado!");
    }
}
