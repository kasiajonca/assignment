package com.assignment.model;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("COLLECTION_PRODUCT")
public class ProductRef {
    Long productId;
}
