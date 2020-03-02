package com.assignment.repositories;

import com.assignment.TakehomeApplication;
import com.assignment.model.Content;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TakehomeApplication.class)
@AutoConfigureTestDatabase
public class ContentRepositoryTest {

    @Autowired
    private ContentRepository contentRepository;

    @Test
    public void findByProductIdAndLocaleTest() {
        Optional<Content> content = contentRepository.findByProductIdAndLocale(1L, "en_CA");
        Assert.assertTrue(content.isPresent());
        Assert.assertEquals(2L, content.get().getId().longValue());
        Assert.assertEquals(1L, content.get().getProductId().longValue());
        Assert.assertEquals("en_CA", content.get().getLocale());
    }

    @Test
    public void findByProductIdAndLocaleNotFoundTest() {
        Optional<Content> content = contentRepository.findByProductIdAndLocale(1L, "en_US");
        Assert.assertFalse(content.isPresent());
    }
}
