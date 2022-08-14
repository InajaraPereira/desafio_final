package br.com.meli.desafio_final.model.dto;

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

    public static PurchaseOrderTotalPriceDto convertDto(PurchaseOrder purchaseOrder) {
        return new PurchaseOrderTotalPriceDto(purchaseOrder.getTotalPrice());
    }
}
