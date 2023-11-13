package org.ecommerce.products.dto;

import org.ecommerce.products.entity.Category;
import org.ecommerce.products.entity.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductsRequestTest {

    private  ProductsRequest productsRequest;

    @BeforeEach
    void setUp() {

        productsRequest = ProductsRequest.builder()
                .sku("00001")
                .name("Test Product")
                .description("Test Description")
                .category(Category.ELECTRONICS)
                .price(99.99f)
                .manufacturer("Test Manufacturer")
                .supplier("Test Supplier")
                .build();
    }

    @Test
    void testNotNull(){
        assertNotNull(productsRequest);
    }

    @Test
    void testGetSku() {
        assertEquals("00001",productsRequest.getSku());
    }

    @Test
    void testGetName() {
        assertEquals("Test Product",productsRequest.getName());
    }

    @Test
    void testGetDescription() {
        assertEquals("Test Description",productsRequest.getDescription());
    }

    @Test
    void testGetCategory() {
        assertEquals("ELECTRONICS",productsRequest.getCategory().toString());
    }

    @Test
    void testGetPrice() {
        assertEquals(99.99f,productsRequest.getPrice());
    }

    @Test
    void testGetManufacturer() {
        assertEquals("Test Manufacturer",productsRequest.getManufacturer());
    }

    @Test
    void testGetSupplier() {
        assertEquals("Test Supplier",productsRequest.getSupplier());
    }

    @AfterEach
    void tearDown() {
    }


}