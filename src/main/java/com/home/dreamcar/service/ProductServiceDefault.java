package com.home.dreamcar.service;

import com.home.dreamcar.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceDefault {

    @Autowired
    private ProductRepository productRepository;
}
