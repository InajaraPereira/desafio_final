package br.com.meli.desafio_final.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentByCredicardDto {

    Double fullPayment;
    Date paymentDate;
    String credicarNumber;
    String Flag;
    String nameInCard;

    public PaymentByCredicardDto(Object fullPayment, Object paymentDate, Object credicarNumber,
                                 Object flag, Object nameInCard) {
        this.fullPayment = (Double) fullPayment;
        this.paymentDate = (Date) paymentDate;
        this.credicarNumber = (String) credicarNumber;
        this.Flag = (String) flag;
        this.nameInCard = (String) nameInCard;
    }
}
