package com.ctse.microservices.product_service.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String id; // Use String or ObjectId, NOT Long

    private String name;
    private String description;
    private BigDecimal price;
}

