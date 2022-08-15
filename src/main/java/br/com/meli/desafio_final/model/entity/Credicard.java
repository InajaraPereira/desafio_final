package br.com.meli.desafio_final.model.entity;

import br.com.meli.desafio_final.model.enums.Flag;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Credicard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Flag flag;

    private String number;

    private String nameInCard;

    private LocalDate validate;

    private String securityNumber;

}
