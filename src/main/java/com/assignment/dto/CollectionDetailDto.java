package com.assignment.dto;

import com.assignment.model.CountryAndLanguage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class CollectionDetailDto {
    private final Long collectionId;
    private final CountryAndLanguage countryAndLanguage;
    private final String name;
    private final Set<ProductDto> products;
}
