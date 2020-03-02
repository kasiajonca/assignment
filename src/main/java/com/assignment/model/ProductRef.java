package com.assignment.model;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Special class used by Spring Data JDBC to manage many to many
 * relationship between collection and product.
 */
@Data
@Table("COLLECTION_PRODUCT")
public class ProductRef {
    Long productId;
}
