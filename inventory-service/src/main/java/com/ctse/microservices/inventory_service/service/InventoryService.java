package com.ctse.microservices.inventory_service.service;

import com.ctse.microservices.inventory_service.model.Inventory;
import com.ctse.microservices.inventory_service.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for handling inventory-related business logic.
 */
@Service
public class InventoryService {

    private final InventoryRepository repo;

    /**
     * Constructor-based dependency injection for InventoryRepository.
     *
     * @param repo the repository used to perform CRUD operations on Inventory data
     */
    public InventoryService(InventoryRepository repo) {
        this.repo = repo;
    }

    /**
     * Retrieves inventory data for a specific product ID.
     *
     * @param productId the ID of the product
     * @return an Optional containing the inventory if found, otherwise empty
     */
    public Optional<Inventory> getInventoryByProductId(String productId) {
        return repo.findByProductId(productId);
    }

    /**
     * Saves or updates an inventory document in the database.
     *
     * @param inventory the inventory object to be saved
     * @return the saved inventory object
     */
    public Inventory save(Inventory inventory) {
        return repo.save(inventory);
    }

    /**
     * Deducts stock from the inventory for a given product, if sufficient quantity exists.
     *
     * @param productId the ID of the product to update
     * @param quantity  the amount of stock to deduct
     * @return true if the stock was successfully deducted; false if not enough stock or product not found
     */
    public boolean deductStock(String productId, Integer quantity) {
        Optional<Inventory> opt = repo.findByProductId(productId);
        if (opt.isPresent()) {
            Inventory inv = opt.get();
            if (inv.getQuantity() >= quantity) {
                inv.setQuantity(inv.getQuantity() - quantity); // Reduce stock
                repo.save(inv); // Save updated inventory
                return true; // Deduction successful
            }
        }
        return false; // Not enough stock or product not found
    }
}
