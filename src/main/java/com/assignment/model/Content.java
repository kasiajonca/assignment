package com.assignment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
public class Content {
    @Id
    private Long id;
    private Long productId;
    private String locale;
    private String description;
}
