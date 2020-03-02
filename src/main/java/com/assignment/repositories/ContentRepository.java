package com.assignment.repositories;

import com.assignment.model.Content;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentRepository extends CrudRepository<Content, Long> {
    @Query("select * from content where product_id = :productId and locale = :locale")
    Optional<Content> findByProductIdAndLocale(@Param ("productId") Long id, @Param ("locale") String locale);
}
