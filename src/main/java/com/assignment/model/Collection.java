package com.assignment.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

/**
 * Class describing collection stored in database. Each collection is defined
 * for a country defined by the locale and will have different name based on the
 * locale. There will be possibly many collections for a given id corresponding
 * to various languages.
 */
@Data
@Table
public class Collection {
    @Id
    @ApiModelProperty(notes = "The database generated collection id")
    private Long id;
    private String collectionName;
    private String locale;
    private Set<ProductRef> productRefs = new HashSet<>();

    public void createAndSetProductRefs(Set<Long> productIds) {
        for (Long productId : productIds) {
            ProductRef productRef = new ProductRef();
            productRef.productId = productId;
            productRefs.add(productRef);
        }
    }

}
