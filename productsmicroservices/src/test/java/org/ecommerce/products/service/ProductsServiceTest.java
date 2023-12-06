package org.ecommerce.products.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductsServiceTest {
    @Autowired
    private ProductsService productsService;

//    @Test
//    void suma() {
//        assertEquals(5, productsService.suma(2,3), "All good");
//        assertNotEquals(5, productsService.suma(3,3), "Bad");
//    }
}