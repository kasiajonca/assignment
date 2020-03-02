package com.assignment.services;

import com.assignment.TakehomeApplication;
import com.assignment.model.CountryAndLanguage;
import com.assignment.dto.CollectionDetailDto;
import com.assignment.dto.CollectionDto;
import com.assignment.model.Collection;
import com.assignment.repositories.CollectionRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TakehomeApplication.class)
public class CollectionServiceTest {

    @MockBean
    private CollectionRepository collectionRepository;

    @Autowired
    private CollectionService collectionService;

    @Test
    public void saveCollectionTest() {
        Collection col = createCollection();
        CollectionDto colDto = createCollectionDto();
        Mockito.when(collectionRepository.save(Mockito.any(Collection.class))).thenReturn(col);
        Collection savedCollection = collectionService.saveCollection(colDto);
        Assert.assertNotNull(savedCollection);

    }

    @Test
    public void getAllCollectionsTest() {
        List<Collection> listOfCol = createListOfCollection();
        Mockito.when(collectionRepository.findAll()).thenReturn(listOfCol);

        List<CollectionDetailDto> returnedResult = collectionService.getAllCollections();
        Assert.assertEquals(1, returnedResult.size());
        Assert.assertEquals(CountryAndLanguage.CA_FR, returnedResult.get(0).getCountryAndLanguage());
    }

    // helper functions
    private Collection createCollection() {
        Collection collection = new Collection();
        collection.setId(5L);
        collection.setLocale("fr_CA");
        collection.setCollectionName("ColName");
        Set<Long> prodIds = new HashSet<>();
        prodIds.add(1L);
        collection.createAndSetProductRefs(prodIds);
        return collection;
    }

    private List<Collection> createListOfCollection() {
        List<Collection> list = new ArrayList<>();
        list.add(createCollection());
        return list;
    }


    private CollectionDto createCollectionDto() {
        CollectionDto collectionDto = new CollectionDto();
        collectionDto.setCountryAndLanguage(CountryAndLanguage.CA_FR);
        collectionDto.setName("ColName");
        Set<Long> prodIds = new HashSet<>();
        prodIds.add(1L);
        collectionDto.setProductIds(prodIds);
        return collectionDto;
    }

}
