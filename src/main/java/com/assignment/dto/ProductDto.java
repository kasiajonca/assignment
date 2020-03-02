package com.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Product dto with info about its content
 */
@Data
@AllArgsConstructor
public class ProductDto {
    private Long productId;
    private String productName;
    private String productSize;
    private String content;
}
