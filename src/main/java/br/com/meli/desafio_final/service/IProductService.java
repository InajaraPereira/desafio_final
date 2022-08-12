package br.com.meli.desafio_final.service;

import br.com.meli.desafio_final.dto.BatchesByProductDto;
import br.com.meli.desafio_final.exception.entity.Product;
import br.com.meli.desafio_final.model.enums.Category;

import java.util.List;

public interface IProductService {

    List<Product> findAllProducts();
    List<Product> findByCategory(Category category);
    Product findById(Long id);
    BatchesByProductDto findBatchByProduct(Long id, String sort);
}
