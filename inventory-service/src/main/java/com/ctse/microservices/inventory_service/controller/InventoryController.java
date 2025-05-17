package com.ctse.microservices.inventory_service.controller;

import com.ctse.microservices.inventory_service.model.Inventory;
import com.ctse.microservices.inventory_service.repository.InventoryRepository;
import com.ctse.microservices.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Inventory> getInventory(@PathVariable String productId) {
        return service.getInventoryByProductId(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return service.save(inventory);
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> isInStock(@RequestParam String productId, @RequestParam Integer quantity) {
        return service.getInventoryByProductId(productId)
                .map(inventory -> ResponseEntity.ok(inventory.getQuantity() >= quantity))
                .orElse(ResponseEntity.ok(false));
    }


//    @PutMapping("/deduct")
//    public ResponseEntity<String> deductStock(@RequestParam String productId, @RequestParam Integer quantity) {
//        return service.deductStock(productId, quantity)
//                ? ResponseEntity.ok("Stock updated")
//                : ResponseEntity.badRequest().body("Not enough stock to deduct");
//    }

    @PutMapping("/deduct")
    public Boolean deductStock(@RequestParam String productId, @RequestParam Integer quantity) {
        return service.deductStock(productId, quantity);
    }




}
