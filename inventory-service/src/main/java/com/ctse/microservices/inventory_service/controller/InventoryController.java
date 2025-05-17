package com.ctse.microservices.inventory_service.controller;

import com.ctse.microservices.inventory_service.model.Inventory;
import com.ctse.microservices.inventory_service.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing inventory operations.
 */
@RestController
@RequestMapping("/api/inventory") // Base path for all inventory-related endpoints
public class InventoryController {

    private final InventoryService service;

    /**
     * Constructor-based dependency injection for InventoryService.
     *
     * @param service the inventory service layer
     */
    public InventoryController(InventoryService service) {
        this.service = service;
    }

    /**
     * Endpoint to retrieve inventory by productId.
     * Example: GET /api/inventory/123
     *
     * @param productId the ID of the product to retrieve
     * @return HTTP 200 with inventory data if found, otherwise HTTP 404
     */
    @GetMapping("/{productId}")
    public ResponseEntity<Inventory> getInventory(@PathVariable String productId) {
        return service.getInventoryByProductId(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint to add a new inventory item or update existing one.
     * Example: POST /api/inventory with JSON body
     *
     * @param inventory the inventory object to save
     * @return the saved inventory object
     */
    @PostMapping
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return service.save(inventory);
    }

    /**
     * Endpoint to check if a product has sufficient quantity in stock.
     * Example: GET /api/inventory/check?productId=123&quantity=5
     *
     * @param productId the product ID to check
     * @param quantity  the required quantity
     * @return true if in stock, false otherwise
     */
    @GetMapping("/check")
    public ResponseEntity<Boolean> isInStock(@RequestParam String productId, @RequestParam Integer quantity) {
        return service.getInventoryByProductId(productId)
                .map(inventory -> ResponseEntity.ok(inventory.getQuantity() >= quantity))
                .orElse(ResponseEntity.ok(false));
    }

    /**
     * Endpoint to deduct stock for a given product.
     * Example: PUT /api/inventory/deduct?productId=123&quantity=2
     *
     * @param productId the product ID
     * @param quantity  the quantity to deduct
     * @return true if deduction was successful, false otherwise
     */
    @PutMapping("/deduct")
    public Boolean deductStock(@RequestParam String productId, @RequestParam Integer quantity) {
        return service.deductStock(productId, quantity);
    }
}
