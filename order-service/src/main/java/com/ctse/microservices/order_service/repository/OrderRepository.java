package com.ctse.microservices.order_service.repository;


import com.ctse.microservices.order_service.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
    // Inherits methods like save(), findById(), findAll(), deleteById(), etc.
}
