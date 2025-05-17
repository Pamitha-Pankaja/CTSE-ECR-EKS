package com.ctse.microservices.inventory_service.repository;

import com.ctse.microservices.inventory_service.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repository interface for Inventory documents.
 * Extends MongoRepository to provide basic CRUD operations and custom query methods.
 */
public interface InventoryRepository extends MongoRepository<Inventory, String> {

    /**
     * Custom query method to find an Inventory document by its productId.
     *
     * @param productId the ID of the product
     * @return an Optional containing the Inventory if found, or empty if not found
     */
    Optional<Inventory> findByProductId(String productId);
}