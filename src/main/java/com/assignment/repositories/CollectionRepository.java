package com.assignment.repositories;

import com.assignment.model.Collection;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectionRepository extends CrudRepository<Collection, Long> {
    @Query("select * from collection where id = :id")
    List<Collection> findByCollectionId(@Param("id") Long id);

    @Query("select * from collection where id = :id and locale = :locale")
    Optional<Collection> findByCollectionIdAndLocale(@Param("id") Long id, @Param("locale") String locale);
}
