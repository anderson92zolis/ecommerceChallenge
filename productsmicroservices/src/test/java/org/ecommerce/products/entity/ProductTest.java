package org.ecommerce.products.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {

        product = Product.builder()
                .productId(1)
                .sku("00001")
                .name("Test Product")
                .description("Test Description")
                .category(Category.ELECTRONICS)
                .price(99.99f)
                .manufacturer("Test Manufacturer")
                .supplier("Test Supplier")
                .build();
    }
    @AfterEach
    void tearDown() {
    }

    @Test
    void testProductInitialization() {
        assertNotNull(product);
    }



    @Test
    void testGetProductId(){
        assertEquals(1,product.getProductId(), "not equals");
    }

    @Test
    void testGetSku(){
        assertEquals("00001",product.getSku(), "not equals");
    }


    @Test
    void testGetName(){
        assertEquals("Test Product",product.getName(), "not equals");
    }

    @Test
    void testGetDescription(){
        assertEquals("Test Description",product.getDescription(), "not equals");
    }

    @Test
    void testGetPrice(){
        assertEquals(99.99f,product.getPrice(), "not equals");
    }

    @Test
    void testGetManufacturer(){
        assertEquals("Test Manufacturer",product.getManufacturer(), "not equals");
    }

    @Test
    void testGetSupplier(){
        assertEquals("Test Supplier",product.getSupplier(), "not equals");
    }

    @Test
    void testProductLocalDatetime() {
        assertNull(product.getLocalDatetime()); // As you haven't explicitly set it
    }

    @Test
    void testProductToString() {
        String expectedToString = "Product{" +
                "productId=1, name='Test Product', description='Test Description', category=ELECTRONICS, " +
                "price=99.99, manufacturer='Test Manufacturer', supplier='Test Supplier', localDatetime=null}";
        assertEquals(expectedToString, product.toString());
    }



}