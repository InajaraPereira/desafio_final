package br.com.meli.desafio_final.dto;

import br.com.meli.desafio_final.exception.entity.Adsense;
import br.com.meli.desafio_final.exception.entity.Product;
import br.com.meli.desafio_final.exception.entity.Seller;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdsenseDto {

    private Seller seller;

    private Product product;

    private Double price;

    public AdsenseDto(Adsense adsense) {
        this.seller = adsense.getSeller();
        this.product = adsense.getProduct();
        this.price = adsense.getPrice();
    }

    public static List<AdsenseDto> convertDto(List<Adsense> listAdsense) {
        return listAdsense.stream().map(AdsenseDto::new).collect(Collectors.toList());
    }
}
