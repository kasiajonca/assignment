package com.assignment.controllers;

import com.assignment.model.CountryAndLanguage;
import com.assignment.services.CollectionService;
import com.assignment.dto.CollectionDetailDto;
import com.assignment.dto.CollectionDto;
import com.assignment.model.Collection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
@Api(value="my project", description = "Showing available collections and their content")
public class CollectionController {

    private CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService service) {
        this.collectionService = service;
    }

    @ApiOperation(value = "View a list of available collections")
    @RequestMapping(method = RequestMethod.GET, value = "/collection", produces = "application/json")
    public ResponseEntity<Iterable<CollectionDetailDto>> listCollections() {
        Iterable<CollectionDetailDto> collections = collectionService.getAllCollections();
        return new ResponseEntity<>(collections, HttpStatus.OK);
    }

    @ApiOperation(value = "Find collection with given id", response = List.class)
    @RequestMapping(method = RequestMethod.GET, value = "/collection/{id}", produces = "application/json")
    public ResponseEntity<List<CollectionDetailDto>> findCollection(@PathVariable("id") Long collectionId) {
        List<CollectionDetailDto> collections = collectionService.findCollection(collectionId);
        return new ResponseEntity<>(collections, HttpStatus.OK);
    }

    @ApiOperation(value = "Find collection with given id and locale", response = CollectionDetailDto.class)
    @RequestMapping(method = RequestMethod.GET, value = "/collection/{id}/{locale}", produces = "application/json")
    public ResponseEntity<CollectionDetailDto> findCollection(@PathVariable("id") Long collectionId,
                                                                    @PathVariable("locale") CountryAndLanguage locale) {
        CollectionDetailDto collection = collectionService.findCollectionByIdAndLocale(collectionId, locale.getLocale());
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @ApiOperation(value = "Create collection", response = String.class)
    @RequestMapping(method = RequestMethod.POST, value = "/collection")
    public ResponseEntity<String> createCollection(@RequestBody CollectionDto collectionDto) {
        Collection createdCollection = collectionService.saveCollection(collectionDto);
        return new ResponseEntity<>("Created collection with id: " + createdCollection.getId()
                + " and locale: " + createdCollection.getLocale(), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete collection(s) with given id", response = Boolean.class)
    @RequestMapping(method = RequestMethod.DELETE, value = "/collection/{id}")
    public ResponseEntity<Boolean> deleteCollection(@PathVariable("id") Long collectionId) {
        collectionService.deleteCollection(collectionId);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    @ApiOperation(value = "Update collection with given id", response = Collection.class)
    @RequestMapping(method = RequestMethod.PUT, value = "/collection")
    public ResponseEntity<Collection> updateCollection(@RequestBody Collection collection) {
        Collection updatedCollection = collectionService.updateCollection(collection);
        if (updatedCollection != null) {
            return new ResponseEntity<>(updatedCollection, HttpStatus.OK);
        } else {
            log.info("Collection not updated");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
