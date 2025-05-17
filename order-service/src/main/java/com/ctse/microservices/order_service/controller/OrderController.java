
package com.ctse.microservices.order_service.controller;

import com.ctse.microservices.order_service.model.Order;
import com.ctse.microservices.order_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//This is the order service controller

@RestController // Marks this class as a REST controller
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;


    public OrderController(OrderService service) {
        this.service = service;
    }

    /**
     * Endpoint to place a new order.
     * Receives an Order object in the request body and processes it via the service layer.
     *
     * @param order Order object received from the client
     * @return ResponseEntity with a success message
     */
    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {
        service.placeOrder(order); // Delegates order processing to the service layer
        return ResponseEntity.ok("Order placed successfully!");
    }
}
