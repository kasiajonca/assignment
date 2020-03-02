package com.assignment.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
public class Product {
    @Id
     private Long id;

    private String productName;
    private String colorCode;
    private String productSize;
}
