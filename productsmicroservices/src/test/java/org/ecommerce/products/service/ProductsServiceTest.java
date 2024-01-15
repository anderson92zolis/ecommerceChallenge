package org.ecommerce.products.service;

import org.ecommerce.products.dto.ProductResponse;
import org.ecommerce.products.dto.ProductsRequest;
import org.ecommerce.products.entity.Product;
import org.ecommerce.products.repository.ProductsRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ecommerce.products.entity.Category.CLOTHING;
import static org.ecommerce.products.entity.Category.ELECTRONICS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class ProductsServiceTest {

    @InjectMocks
    ProductsService productsService;
    Product product1;
    Product product2;
    ProductsRequest productRequest1;
    ProductResponse productResponse1;
    @Mock
    private ProductsRepository productsRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        productRequest1 = ProductsRequest.builder().sku("000001").name("name test 1").description("this is product 1").category(CLOTHING).price(11.11f).manufacturer("test manufacturer").supplier("test supplier").build();

        product1 = Product.builder().productId(1).sku("000001").name("name test 1").description("this is product 1").category(CLOTHING).price(11.11f).manufacturer("test manufacturer 1").supplier("test supplier 1").build();

        product2 = Product.builder().productId(2).sku("000002").name("name test 2").description("this is product 2").category(ELECTRONICS).price(22.22f).manufacturer("test manufacturer 2").supplier("test supplier 2").build();

        productResponse1 = ProductResponse.builder().sku("000001").name("name test 1").description("this is product 1").category(CLOTHING).price(11.11f).build();

    }

    @Test
    @DisplayName("save product test")
    @Order(1)
    void saveProductsTest() {

        // when

        when(productsRepositoryMock.save(Mockito.any(Product.class))).thenReturn(product1);

        ProductResponse productResponseAfterSaved = productsService.saveProducts(productRequest1);

        // then

        assertEquals(productResponseAfterSaved.getSku(), productResponse1.getSku());
        assertEquals(productResponseAfterSaved.getDescription(), productResponse1.getDescription());
        assertEquals(productResponseAfterSaved.getName(), productResponse1.getName());
        assertEquals(productResponseAfterSaved.getPrice(), productResponse1.getPrice());

        assertThat(productResponseAfterSaved).isNotNull();

    }

    @Test
    @DisplayName("update product test")
    @Order(2)
    void updateProduct() {

        //when

        int productId = 1;

        when(productsRepositoryMock.findById(productId)).thenReturn(Optional.ofNullable(product1));
        when(productsRepositoryMock.save(product1)).thenReturn(product1);

        ProductResponse productResponseAfterUpdated = productsService.updateProduct(productId, productRequest1);

        // then

        assertEquals(productResponseAfterUpdated.getSku(), productResponse1.getSku());
        assertEquals(productResponseAfterUpdated.getDescription(), productResponse1.getDescription());
        assertEquals(productResponseAfterUpdated.getName(), productResponse1.getName());
        assertEquals(productResponseAfterUpdated.getPrice(), productResponse1.getPrice());

        assertThat(productResponseAfterUpdated).isNotNull();

    }

    @Test
    @DisplayName("delete product test")
    @Order(3)
    void deleteProductById() {

        // when
        int productId = 1;
        when(productsRepositoryMock.existsById(productId)).thenReturn(true);

        productsService.deleteProductById(productId);

        // then

        assertAll(() -> productsService.deleteProductById(productId));

    }

    @Test
    @DisplayName("get all products test")
    @Order(4)
    void getAllProducts() {

        //when

        List<Product> productsList = new ArrayList<>();
        productsList.add(product1);
        productsList.add(product2);

        when(productsRepositoryMock.findAll()).thenReturn(productsList);

        List<ProductResponse> productResponses = productsService.getAllProducts();

        //then

        assertThat(productResponses).isNotNull();
        assertEquals(productResponses.size(), 2);

    }

    @Test
    @DisplayName("get a product by id test")
    @Order(5)
    void getOneProductById() {

        //when

        int productId = 1;

        when(productsRepositoryMock.findById(productId)).thenReturn(Optional.ofNullable(product1));

        ProductResponse productResponseAfterGetOne = productsService.getOneProductById(productId);

        // then

        assertEquals(productResponseAfterGetOne.getSku(), productResponse1.getSku());
        assertEquals(productResponseAfterGetOne.getDescription(), productResponse1.getDescription());
        assertEquals(productResponseAfterGetOne.getName(), productResponse1.getName());
        assertEquals(productResponseAfterGetOne.getPrice(), productResponse1.getPrice());

        assertThat(productResponseAfterGetOne).isNotNull();

    }

    @Test
    @DisplayName("get one product by sku test")
    @Order(6)
    void findBySku() {
        //when

        String sku = "000001";

        when(productsRepositoryMock.getBySku(sku)).thenReturn(product1);

        ProductResponse productResponseBySku = productsService.findBySku(sku);

        // then

        assertEquals(productResponseBySku.getSku(), productResponse1.getSku());
        assertEquals(productResponseBySku.getDescription(), productResponse1.getDescription());
        assertEquals(productResponseBySku.getName(), productResponse1.getName());
        assertEquals(productResponseBySku.getPrice(), productResponse1.getPrice());

        assertThat(productResponseBySku).isNotNull();

    }

    @Test
    @DisplayName("confirm product by sku test")
    @Order(7)
    void confirmProductBySku() {

        //when

        String sku = "000001";

        when(productsRepositoryMock.getBySku(sku)).thenReturn(product1);

        boolean productBySkuExist = productsService.confirmProductBySku(sku);

        // then

        assertTrue(productBySkuExist);

    }

    @Test
    @DisplayName("mapping product respond test")
    @Order(8)
    void mapToProductResponseTest() {

        //when
        ProductResponse productResponseMapping = productsService.mapToProductResponse(product1);

        // then

        assert (productResponseMapping.equals(productResponse1));

    }
}




