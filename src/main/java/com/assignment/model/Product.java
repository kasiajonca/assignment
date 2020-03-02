package com.assignment.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Class describing product stored in database. Product is
 * localization independent.
 */
@Data
@Table
public class Product {
    @Id
     private Long id;

    private String productName;
    private String colorCode;
    private String productSize;
}
