package br.com.meli.desafio_final.model.entity;

import br.com.meli.desafio_final.model.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private LocalDateTime paymentDate;

    private Double fullPayment;

    @OneToOne
    @JoinColumn(name = "purchase_order_id")
    @JsonIgnoreProperties(value = {"itemList"}, allowSetters = true)
    private PurchaseOrder purchaseOrder;

}
