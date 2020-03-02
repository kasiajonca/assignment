package com.assignment.converters;

import com.assignment.dto.CollectionDetailDto;
import com.assignment.exceptions.EntityNotFoundException;
import com.assignment.model.Collection;
import com.assignment.model.CountryAndLanguage;
import com.assignment.model.Product;
import com.assignment.model.ProductRef;
import com.assignment.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class that converts database model for collection into dto presented
 * to the client of rest services. Because Spring Data JDBC does not do much
 * behind the scenes, we need to get some info from repositories ourselves.
 */
@Component
public class CollectionConverter {
    private ProductRepository productRepository;
    private ProductConverter productConverter;

    @Autowired
    public CollectionConverter(ProductRepository productRepository,
                               ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    /**
     * Convert from List of collections to List of collections detail dto.
     * @param collections to be converted
     * @return list of converted collections
     */
    public List<CollectionDetailDto> convertToListOfCollectionDetailDto(List<Collection> collections) {
        return collections
                .stream()
                .map(this::convertToCollectionDetailDto)
                .collect(Collectors.toList());
    }

    /**
     * Convert from collection to collection detail dto
     * @param collection to be converted
     * @return converted collection
     */
    public CollectionDetailDto convertToCollectionDetailDto(Collection collection) {
        CollectionDetailDto collectionDetailDto = new CollectionDetailDto(collection.getId(),
                CountryAndLanguage.getFromLocale(collection.getLocale()),
                collection.getCollectionName(), new HashSet<>());
        for (ProductRef productRef : collection.getProductRefs()) {
            Optional<Product> product = productRepository.findById(productRef.getProductId());
            if (product.isPresent()) {
                collectionDetailDto.getProducts()
                        .add(productConverter.convertToProductDto(product.get(), collection.getLocale()));
            } else {
                throw new EntityNotFoundException("Product with id: " + productRef.getProductId() + " not found");
            }
         }
        return collectionDetailDto;
    }
}
