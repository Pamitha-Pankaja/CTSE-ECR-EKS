package com.ctse.microservices.inventory_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class represents the Inventory document stored in the MongoDB 'inventory' collection.
 * It uses Lombok annotations for boilerplate code reduction and Spring Data MongoDB annotations
 * for mapping the class to the collection.
 */
@Document(collection = "inventory") // Maps this class to the 'inventory' collection in MongoDB
@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor // Lombok: generates a no-args constructor
@AllArgsConstructor // Lombok: generates a constructor with all fields
public class Inventory {

    @Id
    private String id; // Unique identifier for the inventory document (MongoDB automatically generates an ObjectId if not set)

    private String productId; // Identifier for the product associated with this inventory entry (should match Product ID in Product service)

    private Integer quantity; // Quantity of the product available in inventory
}