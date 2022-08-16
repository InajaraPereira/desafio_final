package br.com.meli.desafio_final.controller;

import br.com.meli.desafio_final.model.entity.Credicard;
import br.com.meli.desafio_final.model.entity.Payment;
import br.com.meli.desafio_final.model.entity.Pix;
import br.com.meli.desafio_final.model.entity.Ticket;
import br.com.meli.desafio_final.service.implementation.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<Payment>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.findyById(id));
    }

    @GetMapping("/credicard/{idPayment}")
    public ResponseEntity<Object> findPaymentByCredicard(@PathVariable Long idPayment) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.findPaymentByCredicard(idPayment));
    }

    @PostMapping("/credicard/{idOrder}")
    public ResponseEntity<Payment> paymenteByCredicard(@RequestBody Credicard credicard, @PathVariable Long idOrder) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.paymenteByCredicard(credicard, idOrder));
    }

    @PostMapping("/pix/{idOrder}")
    public ResponseEntity<Payment> paymentByPix(@RequestBody Pix pix, @PathVariable Long idOrder) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.paymentByPix(pix, idOrder));
    }

    @PostMapping("/ticket/{idOrder}")
    public ResponseEntity<Payment> paymentByTicket(@RequestBody Ticket ticket, @PathVariable Long idOrder) {
        return ResponseEntity.status(HttpStatus.OK).body(paymentService.paymentByTicket(ticket, idOrder));
    }

}
