package com.ctse.microservices.order_service.controller;

import com.ctse.microservices.order_service.model.Order;
import com.ctse.microservices.order_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {
        String result = service.placeOrder(order);
        return ResponseEntity.ok(result);
    }

    // ✅ READ ALL
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(service.getAllOrders());
    }

    // ✅ READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        Optional<Order> order = service.getOrderById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable String id, @RequestBody Order updatedOrder) {
        boolean updated = service.updateOrder(id, updatedOrder);
        if (updated) {
            return ResponseEntity.ok("Order updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable String id) {
        boolean deleted = service.deleteOrder(id);
        if (deleted) {
            return ResponseEntity.ok("Order deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
