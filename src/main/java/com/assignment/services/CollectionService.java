package com.assignment.services;

import com.assignment.converters.CollectionConverter;
import com.assignment.dto.CollectionDetailDto;
import com.assignment.dto.CollectionDto;
import com.assignment.exceptions.EntityNotFoundException;
import com.assignment.model.Collection;
import com.assignment.repositories.CollectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Collection service has business logic for creating, modifying, deleting and
 * listing collections.
 */
@Service
@Slf4j
public class CollectionService {

    private CollectionRepository collectionRepository;
    private CollectionConverter collectionConverter;

    @Autowired
    public CollectionService(CollectionRepository collectionRepository,
                             CollectionConverter collectionConverter) {
        this.collectionRepository = collectionRepository;
        this.collectionConverter = collectionConverter;
    }

    public Collection saveCollection(CollectionDto collectionDto) {
        Collection collection = new Collection();
        collection.setLocale(collectionDto.getCountryAndLanguage().getLocale());
        collection.setCollectionName(collectionDto.getName());
        collection.createAndSetProductRefs(collectionDto.getProductIds());

        return collectionRepository.save(collection);
    }

    public List<CollectionDetailDto> getAllCollections() {
        Iterable<Collection> collections = collectionRepository.findAll();
        List<CollectionDetailDto> collectionDetailDtos = new ArrayList<>();
        for (Collection collection : collections) {
            CollectionDetailDto collectionDetailDto = collectionConverter.convertToCollectionDetailDto(collection);
            collectionDetailDtos.add(collectionDetailDto);
        }
        return collectionDetailDtos;
    }

    public void deleteCollection(Long collectionId) {
         collectionRepository.deleteById(collectionId);
    }

    public Collection updateCollection(Collection collection) {
        Optional<Collection> existingCollection = collectionRepository.findById(collection.getId());
        if (existingCollection.isPresent()) {
            return collectionRepository.save(collection);
        }
        log.info("Collection with id {} not found and cannot be updated", collection.getId());
        return null;
    }

    public List<CollectionDetailDto> findCollection(Long collectionId) {
        List<Collection> collections = collectionRepository.findByCollectionId(collectionId);
        if (collections.isEmpty()) {
            throw new EntityNotFoundException("Collection with id: " + collectionId + " not found.");
        }
        return collectionConverter.convertToListOfCollectionDetailDto(collections);
    }

    public CollectionDetailDto findCollectionByIdAndLocale(Long collectionId, String locale) {
    Optional<Collection> collectionOpt = collectionRepository.findByCollectionIdAndLocale(collectionId, locale);
        return collectionConverter.convertToCollectionDetailDto(
                collectionOpt.orElseThrow(() -> {throw new EntityNotFoundException(
                        String.format("Collection with id: %d and locale: %s not found.", collectionId, locale));}));
    }
}
