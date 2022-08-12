package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.exception.entity.Buyer;

public interface IBuyerService {

    Buyer findById(Long id);

}
