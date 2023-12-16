package org.ecommerce.products.repository;

import org.ecommerce.products.entity.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.ecommerce.products.entity.Category.*;

@Testcontainers
@SpringBootTest
class ProductsRepositoryTest {

    // https://tipsontech.medium.com/integration-testing-of-springboot-with-postgres-using-testcontainers-a00bd288b909
    // https://github.com/sauravkumarshah/spring-boot-with-postgresql/blob/main/src/test/java/com/kantar/template/SpringBootPostgresqlApplicationTests.java
    // https://testcontainers.com/guides/testing-spring-boot-rest-api-using-testcontainers/
    // https://www.google.com/search?q=create+testing+%40testcontainers+%40springboottest+using+postgresql&sca_esv=591223588&rlz=1C1CHBD_esES1075ES1075&sxsrf=AM9HkKkfVbGLacKRsDCGJvf3kZwhO6N7hQ%3A1702650604985&ei=7GJ8ZY3aO52hkdUP5MKsqAo&oq=create+testing+%40Testcontainers+%40SpringBootTest+using+postgre&gs_lp=Egxnd3Mtd2l6LXNlcnAiPGNyZWF0ZSB0ZXN0aW5nIEBUZXN0Y29udGFpbmVycyBAU3ByaW5nQm9vdFRlc3QgdXNpbmcgcG9zdGdyZSoCCAEyBRAhGKABMgQQIRgVSKA8UOsBWKopcAF4AZABAJgBlgGgAYUMqgEENC4xMLgBA8gBAPgBAcICChAAGEcY1gQYsAPCAgcQIRigARgK4gMEGAAgQYgGAZAGCA&sclient=gws-wiz-serp

    @Autowired
    ProductsRepository productsRepository;

    private Product product1;

    private Product product2;


    // properties
    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("products")
            .withUsername("ecommerce")
            .withPassword("password")
           ;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgresContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @BeforeAll
    static void beforeAll() {
        postgresContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgresContainer.stop();
    }

    @BeforeEach
    void setUp() {

        //clean the postgresql db

        productsRepository.deleteAll();

        product1 = Product
                .builder()
                .productId(1)
                .sku("000001")
                .name("name test 1")
                .description("this is product 1")
                .category(CLOTHING)
                .price(11.11f)
                .manufacturer("test manufacturer 1")
                .supplier("test supplier 1")
                .build();

        product2 = Product
                .builder()
                .productId(2)
                .sku("000002")
                .name("name test 2")
                .description("this is product 2")
                .category(ELECTRONICS)
                .price(22.22f)
                .manufacturer("test manufacturer 2")
                .supplier("test supplier 2")
                .build();

        productsRepository.save(product1);
        productsRepository.save(product2);
        //productsRepository.flush();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(value = 1)
    @DisplayName("connection test")
    void testConnectionToDatabase() {
        Assertions.assertNotNull(productsRepository);
    }


    @Test
    @Order(value = 2)
    @DisplayName("find product by id")
    void findById() throws Exception {

        // TESTING WHEN THE PRODUCT EXIST WITH THE ID


        //given
        int productIdExist= 6;

        // when
        Optional<Product> productExist = productsRepository.findById(productIdExist);

        // then

        Assertions.assertTrue(productExist.isPresent(), "Product with ID " + productIdExist + " should be present");

        // TESTING WHEN THE PRODUCT DOESN'T EXIST WITH THE ID

        //given
        int productIdDoesNotExist= 1000;

        // when
        Optional<Product> productDoesNotExist = productsRepository.findById(productIdDoesNotExist);

        // then
        Assertions.assertTrue(!productDoesNotExist.isPresent());

    }

    @Test
    @Order(value = 3)
    @DisplayName("save product test")
    void saveProduct() throws Exception {

        //given
        Product product3 = Product
                .builder()
                .productId(3)
                .sku("000003")
                .name("name test 3")
                .description("this is product 3")
                .category(AUTOMOTIVE)
                .price(33.33f)
                .manufacturer("test manufacturer 3")
                .supplier("test supplier 3")
                .build();

        productsRepository.save(product3);

        //when
        List<Product> productsList = productsRepository.findAll();

        //then
        Assertions.assertEquals(3, productsList.size());

        Assertions.assertNotEquals(0, productsList.size());

    }



    @Test
    @Order(value = 4)
    @DisplayName("save all products test")
    void findAllProduct() throws Exception {

        //given
            // already saved two products

        // when
        List<Product> productsList = productsRepository.findAll();

        // then

        //the size of obtained products are the same with the products saved in DB
        Assertions.assertEquals(2, productsList.size());

        //the size of obtained products aren't the same with the products saved in DB
        Assertions.assertNotEquals(0, productsList.size());
    }

    @Test
    @Order(value = 5)
    @DisplayName("update product test")
    void updateProduct() throws Exception {

        // WHEN THE ID EXIST
        //when

        int  productIdExist= 1; // already exist

        //given

        Product productToSave = Product
                .builder()
                .productId(productIdExist)
                .sku("11111111")
                .name("name test 1 updated")
                .description("this is product 1 updated")
                .category(CLOTHING)
                .price(33.33f)
                .manufacturer("test manufacturer 1 updated")
                .supplier("test supplier 1 updated")
                .build();

        Product productupdatedAfterSaved = productsRepository.save(productToSave); // it is going save overwriting the changes
        List<Product> productsList = productsRepository.findAll();

        //then
        Assertions.assertEquals(productsList.size(),2); // to verify if the size of BD is the same
        Assertions.assertEquals(productToSave.getProductId(),productupdatedAfterSaved.getProductId());
        Assertions.assertEquals(productToSave.getSku(), productupdatedAfterSaved.getSku());
        Assertions.assertEquals(productToSave.getDescription(), productupdatedAfterSaved.getDescription());
        Assertions.assertEquals(productToSave.getCategory(), productupdatedAfterSaved.getCategory());
        Assertions.assertEquals(productToSave.getCategory(), productupdatedAfterSaved.getCategory());
        Assertions.assertEquals(productToSave.getPrice(), productupdatedAfterSaved.getPrice());
        Assertions.assertEquals(productToSave.getManufacturer(), productupdatedAfterSaved.getManufacturer());
        Assertions.assertEquals(productToSave.getSupplier(), productupdatedAfterSaved.getSupplier());

        // WHEN THE ID DOES NOT EXIST

        //when

        int  productIdDoesNotExist= 8; // already exist

        //given

        productToSave.setName("newProduct");
        productToSave.setProductId(productIdDoesNotExist);


        Product productupdatedAfterSavedNotExist= productsRepository.save(productToSave); // save in database like new product
        List<Product> productsListExist = productsRepository.findAll();

        //then
        Assertions.assertEquals(productsListExist.size(),3); // to verify if the size of BD is the same

    }

    @Test
    @Order(value = 6)
    @DisplayName("delete a product by id")
    void deleteProduct() throws Exception {

        //given
        int productId=2;
        // already saved two products

        // when
        productsRepository.deleteById(2);

        // then

        //the size of obtained products are the same with the products saved in DB
        Assertions.assertTrue(productsRepository.findById(productId).isEmpty());


    }

    @Test
    @Order(value = 6)
    @DisplayName("get product by SKU")
    void getBySku() {

        // TESTING WHEN THE PRODUCT EXISTS WITH THE SKU

        //given

        String skuCorrect= "000002";

        // when

        Product productExist = productsRepository.getBySku(skuCorrect);

        // then

        Assertions.assertEquals(productExist.getSku(), product2.getSku());

        // TESTING WHEN THE PRODUCT DOESN'T EXIST WITH THE ID

        //given
        String skuIncorrect= "000006";

        // when
        Product productIdDoesNotExist = productsRepository.getBySku(skuIncorrect);;

        // then
        Assertions.assertNull(productIdDoesNotExist);

    }



}