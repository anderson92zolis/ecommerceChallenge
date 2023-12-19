package org.ecommerce.products.service;

import org.ecommerce.products.dto.ProductResponse;
import org.ecommerce.products.dto.ProductsRequest;
import org.ecommerce.products.entity.Product;
import org.ecommerce.products.exception.ProductNotFound;
import org.ecommerce.products.repository.ProductsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ecommerce.products.entity.Category.CLOTHING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class ProductsServiceTest {

    @Mock
    private ProductsRepository productsRepositoryMock;
    @InjectMocks
    ProductsService productsService;

    Product product1;
    ProductsRequest productRequest1;

    ProductResponse productResponse1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        productRequest1 = ProductsRequest
                .builder()
                .sku("000001")
                .name("name test 1")
                .description("this is product 1")
                .category(CLOTHING)
                .price(11.11f)
                .manufacturer("test manufacturer")
                .supplier("test supplier")
                .build();

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

        productResponse1 = ProductResponse.builder()
                .sku("000001")
                .name("name test 1")
                .description("this is product 1")
                .category(CLOTHING)
                .price(11.11f)
                .build();

    }


    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("save product test")
    void saveProductsTest() {

        // when

        when(productsRepositoryMock.save(Mockito.any(Product.class))).thenReturn(product1);

        ProductResponse productResponseAfterSaved =  productsService.saveProducts(productRequest1);

        // then

        assertEquals(productResponseAfterSaved.getSku(), productResponse1.getSku());
        assertEquals(productResponseAfterSaved.getDescription(), productResponse1.getDescription());
        assertEquals(productResponseAfterSaved.getName(), productResponse1.getName());
        assertEquals(productResponseAfterSaved.getPrice(), productResponse1.getPrice());

        assertThat(productResponseAfterSaved).isNotNull();

    }



    @Test
    void updateProduct() {

        //when

        int  productId= 1;

        when(productsRepositoryMock.findById(productId)).thenReturn(Optional.ofNullable(product1));
        when( productsRepositoryMock.save(product1)).thenReturn(product1);

        ProductResponse productResponseAfterUpdated =  productsService.updateProduct(productId,productRequest1);

        // then

        assertEquals(productResponseAfterUpdated.getSku(), productResponse1.getSku());
        assertEquals(productResponseAfterUpdated.getDescription(), productResponse1.getDescription());
        assertEquals(productResponseAfterUpdated.getName(), productResponse1.getName());
        assertEquals(productResponseAfterUpdated.getPrice(), productResponse1.getPrice());

        assertThat(productResponseAfterUpdated).isNotNull();


    }

    @Test
    void deleteProductById() {


    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getOneProductById() {
    }

    @Test
    void findBySku() {
    }

    @Test
    void confirmProductBySku() {
    }
}




