package com.assignment.converters;

import com.assignment.dto.ProductDto;
import com.assignment.model.Content;
import com.assignment.model.Product;
import com.assignment.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
/**
 * Class that converts database model for product into dto presented
 * to the client of rest services. Because Spring Data JDBC does not do much
 * behind the scenes, we need to get some info from repositories ourselves.
 */
@Component
public class ProductConverter {
    private ContentRepository contentRepository;

    @Autowired
    public ProductConverter(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public ProductDto convertToProductDto(Product product, String locale) {
        Optional<Content> contentOpt = contentRepository.findByProductIdAndLocale(product.getId(), locale);
        String content = "Content for this language does not exist.";
        return new ProductDto(product.getId(), product.getProductName(), product.getProductSize(),
                contentOpt.isPresent() ? contentOpt.get().getDescription() : content);
    }

}
