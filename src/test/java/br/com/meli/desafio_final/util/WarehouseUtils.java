package br.com.meli.desafio_final.util;

import br.com.meli.desafio_final.exception.entity.Warehouse;


public class WarehouseUtils {
    public static Warehouse newWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(1L);
        warehouse.setName("Warehouse");
        return warehouse;
    }
}
