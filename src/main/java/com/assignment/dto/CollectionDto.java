package com.assignment.dto;

import com.assignment.model.CountryAndLanguage;
import lombok.Data;

import java.util.Set;

/**
 * Collection dto shared with clients. Contains only ids of
 * products belonging to the collection.
 */
@Data
public class CollectionDto {
    private CountryAndLanguage countryAndLanguage;
    private String name;
    Set<Long> productIds;
}
