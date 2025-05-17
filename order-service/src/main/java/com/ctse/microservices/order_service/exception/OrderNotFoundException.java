package com.ctse.microservices.order_service.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String id) {
        super("No order found with ID: " + id);
    }
}
