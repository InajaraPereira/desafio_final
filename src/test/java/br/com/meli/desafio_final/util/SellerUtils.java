package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.exception.entity.Seller;

public class SellerUtils {

    public static Seller newSeller1ToSave() {
        return Seller.builder()
                .id(1L)
                .name("Joao")
                .build();
    }

    public static Seller newSeller2ToSave() {
        return Seller.builder()
                .id(2L)
                .name("Nicolas")
                .build();
    }

}
