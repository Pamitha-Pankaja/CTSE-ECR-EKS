package com.ctse.microservices.order_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private String id; // ✅ Changed from Long to String

    private String productId; // also changed from Long to String
    private Integer quantity;
    private String status;
}


