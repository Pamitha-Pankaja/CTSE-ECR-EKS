package com.ctse.microservices.product_service.controller;

import com.ctse.microservices.product_service.model.Product;
import com.ctse.microservices.product_service.repository.ProductRepository;
import com.ctse.microservices.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a RESTful web service controller
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    // Constructor-based dependency injection of ProductService
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping // Handles GET requests to /api/products
    public List<Product> getAll() {
        // Fetch all products from the service
        return service.getAll();
    }

    @PostMapping // Handles POST requests to /api/products
    public Product create(@RequestBody Product product) {
        // Save the new product via the service
        return service.save(product);
    }
}
