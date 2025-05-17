package com.ctse.microservices.inventory_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    private String id; // ✅ Changed from Long to String

    private String productId; // also recommended to be String to match Mongo ID format
    private Integer quantity;
}


