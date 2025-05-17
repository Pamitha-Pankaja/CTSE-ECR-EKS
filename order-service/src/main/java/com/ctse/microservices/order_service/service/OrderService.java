package com.ctse.microservices.order_service.service;

import com.ctse.microservices.order_service.client.InventoryClient;
import com.ctse.microservices.order_service.exception.OrderNotFoundException;
import com.ctse.microservices.order_service.model.Order;
import com.ctse.microservices.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marks this class as a Spring service component
public class OrderService {

    private final OrderRepository repo; // Repository to interact with MongoDB
    private final InventoryClient inventoryClient; // Feign client to call Inventory microservice

    // Constructor injection of dependencies
    public OrderService(OrderRepository repo, InventoryClient inventoryClient) {
        this.repo = repo;
        this.inventoryClient = inventoryClient;
    }

    // Handles placing an order after checking inventory
    public String placeOrder(Order order) {
        try {
            Boolean inStock = inventoryClient.isInStock(order.getProductId(), order.getQuantity());

            if (Boolean.TRUE.equals(inStock)) {
                order.setStatus("PLACED"); // Set status as placed
                repo.save(order); // Save to MongoDB
                inventoryClient.deductStock(order.getProductId(), order.getQuantity()); // Deduct stock
                return "Order placed successfully";
            } else {
                return "Insufficient stock"; // If stock is not available
            }
        } catch (Exception ex) {
            // Catch any exception (e.g., Feign client failure) and rethrow
            throw new RuntimeException("Failed to place order: " + ex.getMessage());
        }
    }

    // Returns all orders
    public List<Order> getAllOrders() {
        return repo.findAll();
    }

    // Returns a single order by ID or throws exception if not found
    public Optional<Order> getOrderById(String id) {
        Optional<Order> order = repo.findById(id);
        if (order.isEmpty()) {
            throw new OrderNotFoundException(id);
        }
        return order;
    }

    // Updates an existing order by ID
    public boolean updateOrder(String id, Order updatedOrder) {
        Order order = repo.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        order.setProductId(updatedOrder.getProductId());
        order.setQuantity(updatedOrder.getQuantity());
        order.setStatus(updatedOrder.getStatus());
        repo.save(order);
        return true;
    }

    // Deletes an order by ID if it exists, otherwise throws exception
    public boolean deleteOrder(String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        } else {
            throw new OrderNotFoundException(id);
        }
    }
}
