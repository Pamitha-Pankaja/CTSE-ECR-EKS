package com.ctse.microservices.order_service.model;

import lombok.*; // Lombok annotations for reducing boilerplate code
import org.springframework.data.annotation.Id; // Marks the primary key field
import org.springframework.data.mongodb.core.mapping.Document; // Maps class to MongoDB document

/**
 * This class represents an Order document in the MongoDB collection "orders".
 * It is part of the Order microservice in a microservices' architecture.
 */
@Document(collection = "orders") // Specifies the MongoDB collection name
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Lombok annotation to generate a no-arguments constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
public class Order {

    @Id // Marks this field as the primary identifier for MongoDB
    private String id; // Changed from Long to String to match MongoDB's default ObjectId type

    private String productId; // ID of the product being ordered (changed from Long to String for consistency)
    private Integer quantity; // Number of units ordered
    private String status; // Current status of the order (e.g., "PENDING", "SHIPPED", "DELIVERED")
}


