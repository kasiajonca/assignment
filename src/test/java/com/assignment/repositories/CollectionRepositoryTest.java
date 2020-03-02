package com.assignment.repositories;

import com.assignment.TakehomeApplication;
import com.assignment.model.Collection;
import org.junit.Assert;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TakehomeApplication.class)
public class CollectionRepositoryTest {
    @Autowired
    private CollectionRepository collectionRepository;

    @Test
    public void findByCollectionIdTest() {
        List<Collection> result = collectionRepository.findByCollectionId(1L);
        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
        long colId  = result.get(0).getId();
         Assert.assertEquals(1L, colId);
    }

    @Test
    public void findByCollectionIdAndLocaleTest() {
        Optional<Collection> result = collectionRepository.findByCollectionIdAndLocale(1L, "en_CA");
        Assert.assertTrue(result.isPresent());
        long colId  = result.get().getId();
        String locale = result.get().getLocale();
        Assert.assertEquals(1L, colId);
        Assert.assertEquals("en_CA", locale);
    }

    @Test
    public void findByCollectionIdAndLocaleNotFoundTest() {
        Optional<Collection> result = collectionRepository.findByCollectionIdAndLocale(1L, "en_US");
        Assert.assertFalse(result.isPresent());
    }
}
