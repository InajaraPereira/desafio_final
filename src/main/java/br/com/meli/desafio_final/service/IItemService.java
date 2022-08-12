package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.exception.entity.Item;

import java.util.List;

public interface IItemService {

    void save(Item item);

    List<Item> findItemsByPurchaseOrderId(Long id);

}
