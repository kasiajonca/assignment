package com.assignment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Class describing content stored in database. Each content belongs to
 * a product but has description in a language defined by locale.
 */
@Data
@Table
public class Content {
    @Id
    private Long id;
    private Long productId;
    private String locale;
    private String description;
}
