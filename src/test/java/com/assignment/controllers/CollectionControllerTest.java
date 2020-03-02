package com.assignment.controllers;

import com.assignment.dto.ProductDto;
import com.assignment.model.CountryAndLanguage;
import com.assignment.services.CollectionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.assignment.dto.CollectionDetailDto;
import com.assignment.exceptions.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest()
public class CollectionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CollectionService collectionService;

    @Test
    public void whenListCollection_return200() throws Exception {
        doReturn(new ArrayList<CollectionDetailDto>()).when(collectionService).getAllCollections();
        mockMvc.perform(get("/product/collection"))
               .andExpect(status().isOk());
        verify(collectionService, times(1)).getAllCollections();
    }

    @Test
    public void whenFindCollectionById_return200() throws Exception{
        List<CollectionDetailDto> result = createListOfCollectionDetailsDto();
        when(collectionService.findCollection(anyLong())).thenReturn(result);
        mockMvc.perform(get("/product/collection/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(result)));
        verify(collectionService, times(1)).findCollection(anyLong());
    }

    @Test
    public void whenCollectionNotFound_return404() throws Exception {
        when(collectionService.findCollection(anyLong())).thenThrow(new EntityNotFoundException("Collection not found."));
        mockMvc.perform(get("/product/collection/1"))
                .andExpect(status().isNotFound());
        verify(collectionService, times(1)).findCollection(anyLong());
    }

    @Test
    public void whenFindCollectionByIdAndLocale_return200() throws Exception{
        CollectionDetailDto result = createCollectionDetailsDto();
        when(collectionService.findCollectionByIdAndLocale(anyLong(), anyString())).thenReturn(result);
        mockMvc.perform(get("/product/collection/1/CA_EN"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(result)));
        verify(collectionService, times(1)).findCollectionByIdAndLocale(anyLong(), anyString());
    }


    //    helper functions
    private List<CollectionDetailDto> createListOfCollectionDetailsDto() {
        List<CollectionDetailDto> list = new ArrayList<>();
        list.add(createCollectionDetailsDto());
        return list;
    }

    private CollectionDetailDto createCollectionDetailsDto() {
        ProductDto productDto = new ProductDto(1L, "shoes", "small", "content");
        Set<ProductDto> products = new HashSet<>();
        products.add(productDto);
        return new CollectionDetailDto(1L, CountryAndLanguage.CA_EN, "ColName", products);
    }
}
