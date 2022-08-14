package br.com.meli.desafio_final.dto;

import br.com.meli.desafio_final.model.entity.PurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderTotalPriceDto {

    private Double totalPrice;

    public PurchaseOrderTotalPriceDto(PurchaseOrder purchaseOrder) {
        this.totalPrice = purchaseOrder.getTotalPrice();
    }

}
