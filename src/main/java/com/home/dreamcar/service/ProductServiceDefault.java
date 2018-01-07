package com.home.dreamcar.service;

import com.home.dreamcar.exception.ErrorAdvice;
import com.home.dreamcar.model.Product;
import com.home.dreamcar.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceDefault implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product find(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public void delete(Long id) {
        Product product = find(id);
        if (product != null) {
            productRepository.delete(id);
        } else {
            throw new ErrorAdvice.NotModifiedDataAccessException("Product already gone");
        }
    }

}
