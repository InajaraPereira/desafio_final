package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.model.entity.Credicard;
import br.com.meli.desafio_final.model.entity.Payment;
import br.com.meli.desafio_final.model.entity.Pix;
import br.com.meli.desafio_final.model.entity.Ticket;
import br.com.meli.desafio_final.model.enums.Flag;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentUtils {
    public static Payment newPayment1ToSave() {
        return Payment.builder()
                .paymentDate(LocalDate.now())
                .fullPayment(7.9)
                .purchaseOrder(PurchaseOrderUtils.newPurchase1ToSave())
                .build();
    }

    public static List<Payment> generatePaymentList() {
        List<Payment> list = new ArrayList<>();
        list.add(newPayment1ToSave());
        return list;
    }

    public static Credicard newCredicard1ToSave(){
        return Credicard.builder()
                .flag(Flag.MASTERCARD)
                .credicardNumber("1234.5698.7896")
                .nameInCard("MARCELA P FARIAS")
                .validate(LocalDate.of(2029,8,16))
                .securityNumber("685")
                .payment(PaymentUtils.newPayment1ToSave())
                .build();
    }

    public static Credicard newCredicard2ToSave(){
        return Credicard.builder()
                .flag(Flag.MASTERCARD)
                .credicardNumber("1234")
                .nameInCard("MARCELA P FARIAS")
                .validate(LocalDate.of(2029,8,16))
                .securityNumber("685")
                .payment(PaymentUtils.newPayment1ToSave())
                .build();
    }

    public static Credicard newCredicard3ToSave(){
        return Credicard.builder()
                .flag(Flag.MASTERCARD)
                .credicardNumber("1234.5698.7896")
                .nameInCard("MARCELA P FARIAS")
                .validate(LocalDate.of(2029,8,16))
                .securityNumber("123456987")
                .payment(PaymentUtils.newPayment1ToSave())
                .build();
    }

    public static Credicard newCredicard4ToSave(){
        return Credicard.builder()
                .flag(Flag.VISA)
                .credicardNumber("123456789101112")
                .nameInCard("MARCELA P FARIAS")
                .validate(LocalDate.of(2029,8,16))
                .securityNumber("365")
                .payment(PaymentUtils.newPayment1ToSave())
                .build();
    }


    public static Pix newPixtoSave(){
        return Pix.builder()
                .number("48999617033")
                .build();
    }

    public static Pix newPix1toSave() {
        return Pix.builder()
                .number("123")
                .build();
    }

    public static Ticket newTickettoSave() {
        return Ticket.builder()
                .number("1234567899874123654789632147789654411369874456321")
                .validate(LocalDate.of(2022,12,12))
                .build();
    }

    public static Ticket newTicket1toSave() {
        return Ticket.builder()
                .number("000")
                .validate(LocalDate.of(2022,12,12))
                .build();
    }

    public static Ticket newTicket2toSave() {
        return Ticket.builder()
                .number("1234567899874123654789632147789654411369874456321")
                .validate(LocalDate.of(2022,8,12))
                .build();
    }
}
