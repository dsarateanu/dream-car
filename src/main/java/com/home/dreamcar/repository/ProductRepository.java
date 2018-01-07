package com.home.dreamcar.repository;

import com.home.dreamcar.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Product save(Product auction);

    void delete(Long id);

    Product findById(Long id);
}
