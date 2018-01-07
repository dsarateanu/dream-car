package com.home.dreamcar.service;

import com.home.dreamcar.model.Product;

public interface ProductService {

    Product find(Long id);

    Product saveOrUpdateProduct(Product auction);

    Iterable<Product> findAll();

    void delete(Long id);
}
