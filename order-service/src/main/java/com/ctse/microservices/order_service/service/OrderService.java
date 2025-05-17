package com.ctse.microservices.order_service.service;

import com.ctse.microservices.order_service.client.InventoryClient;
import com.ctse.microservices.order_service.exception.OrderNotFoundException;
import com.ctse.microservices.order_service.model.Order;
import com.ctse.microservices.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repo;
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepository repo, InventoryClient inventoryClient) {
        this.repo = repo;
        this.inventoryClient = inventoryClient;
    }

    public String placeOrder(Order order) {
        try {
            Boolean inStock = inventoryClient.isInStock(order.getProductId(), order.getQuantity());

            if (Boolean.TRUE.equals(inStock)) {
                order.setStatus("PLACED");
                repo.save(order);
                inventoryClient.deductStock(order.getProductId(), order.getQuantity());
                return "Order placed successfully";
            } else {
                return "Insufficient stock";
            }
        } catch (Exception ex) {
            throw new RuntimeException("Failed to place order: " + ex.getMessage());
        }
    }

    public List<Order> getAllOrders() {
        return repo.findAll();
    }

    public Optional<Order> getOrderById(String id) {
        Optional<Order> order = repo.findById(id);
        if (order.isEmpty()) {
            throw new OrderNotFoundException(id);
        }
        return order;
    }

    public boolean updateOrder(String id, Order updatedOrder) {
        Order order = repo.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        order.setProductId(updatedOrder.getProductId());
        order.setQuantity(updatedOrder.getQuantity());
        order.setStatus(updatedOrder.getStatus());
        repo.save(order);
        return true;
    }

    public boolean deleteOrder(String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        } else {
            throw new OrderNotFoundException(id);
        }
    }
}
