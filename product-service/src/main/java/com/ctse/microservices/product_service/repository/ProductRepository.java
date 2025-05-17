package com.ctse.microservices.product_service.repository;

import com.ctse.microservices.product_service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    // Inherits methods like save(), findById(), findAll(), deleteById(), etc.
}
