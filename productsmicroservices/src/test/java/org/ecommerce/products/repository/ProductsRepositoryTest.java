package org.ecommerce.products.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
class ProductsRepositoryTest {

    // https://tipsontech.medium.com/integration-testing-of-springboot-with-postgres-using-testcontainers-a00bd288b909
    // https://github.com/sauravkumarshah/spring-boot-with-postgresql/blob/main/src/test/java/com/kantar/template/SpringBootPostgresqlApplicationTests.java
    // https://testcontainers.com/guides/testing-spring-boot-rest-api-using-testcontainers/
    // https://www.google.com/search?q=create+testing+%40testcontainers+%40springboottest+using+postgresql&sca_esv=591223588&rlz=1C1CHBD_esES1075ES1075&sxsrf=AM9HkKkfVbGLacKRsDCGJvf3kZwhO6N7hQ%3A1702650604985&ei=7GJ8ZY3aO52hkdUP5MKsqAo&oq=create+testing+%40Testcontainers+%40SpringBootTest+using+postgre&gs_lp=Egxnd3Mtd2l6LXNlcnAiPGNyZWF0ZSB0ZXN0aW5nIEBUZXN0Y29udGFpbmVycyBAU3ByaW5nQm9vdFRlc3QgdXNpbmcgcG9zdGdyZSoCCAEyBRAhGKABMgQQIRgVSKA8UOsBWKopcAF4AZABAJgBlgGgAYUMqgEENC4xMLgBA8gBAPgBAcICChAAGEcY1gQYsAPCAgcQIRigARgK4gMEGAAgQYgGAZAGCA&sclient=gws-wiz-serp

    @Autowired
    ProductsRepository productsRepository;


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

    /*
    @Test
    void getBySku() {
    }

     */


}