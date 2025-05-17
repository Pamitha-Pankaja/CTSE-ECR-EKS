package com.ctse.microservices.product_service.model;

import lombok.*; // Lombok annotations to reduce boilerplate code
import org.springframework.data.annotation.Id; // Marks the primary key for the document
import org.springframework.data.mongodb.core.mapping.Document; // Maps the class to a MongoDB collection

import java.math.BigDecimal;

/**
 * This class represents a Product document stored in the MongoDB "products" collection.
 */
@Document(collection = "products") // Specifies the MongoDB collection name
@Data // Lombok annotation to auto-generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-args constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
public class Product {

    @Id // Marks this field as the primary key in MongoDB
    private String id; // Product ID (String is preferred for MongoDB ObjectId)

    private String name; // Name of the product
    private String description; // Description of the product
    private BigDecimal price; // Price of the product using BigDecimal for accuracy
}
