package com.assignment.dto;

import com.assignment.model.CountryAndLanguage;
import lombok.Data;

import java.util.Set;

@Data
public class CollectionDto {
    private CountryAndLanguage countryAndLanguage;
    private String name;
    Set<Long> productIds;
}
