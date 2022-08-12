package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.exception.entity.Buyer;

public class BuyerUtils {

    public static Buyer newBuyer1ToSave() {
        return Buyer.builder()
            .id(1L)
            .name("Mulher Maravilha")
            .build();
    }

    public static Buyer newBuyer2ToSave() {
        return Buyer.builder()
            .id(2L)
            .name("Steve Trevor")
            .build();
    }

}
