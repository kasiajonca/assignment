package com.assignment.dto;

import com.assignment.model.CountryAndLanguage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

/**
 * Dto used to expose collection data to clients. This includes
 * product data dtos with all info.
 */
@Getter
@AllArgsConstructor
public class CollectionDetailDto {
    private final Long collectionId;
    private final CountryAndLanguage countryAndLanguage;
    private final String name;
    private final Set<ProductDto> products;
}
