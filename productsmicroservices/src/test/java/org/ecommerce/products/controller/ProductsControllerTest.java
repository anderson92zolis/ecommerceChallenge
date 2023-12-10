package org.ecommerce.products.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ecommerce.products.dto.ProductResponse;
import org.ecommerce.products.dto.ProductsRequest;
import org.ecommerce.products.entity.Product;
import org.ecommerce.products.repository.ProductsRepository;
import org.ecommerce.products.service.ProductsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.ecommerce.products.entity.Category.CLOTHING;
import static org.ecommerce.products.entity.Category.ELECTRONICS;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProductsController.class)
class ProductsControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductsService productsServiceMock;

    @Autowired
    private ObjectMapper objectMapper;

    private Product product1;
    private Product product2;

    private ProductResponse productResponse1;
    private ProductResponse productResponse2;

    private ProductsRequest productRequest1;
    private ProductsRequest productRequest2;


    @BeforeEach
    void setUp() {

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

        productRequest2 = ProductsRequest
                .builder()
                .sku("000002")
                .name("name test 2")
                .description("this is product 2")
                .category(ELECTRONICS)
                .price(22.22f)
                .manufacturer("test manufacturer 2")
                .supplier("test supplier 2")
                .build();


        product1 = Product.builder()
                .sku("000001")
                .name("name test 1")
                .description("this is product 1")
                .category(CLOTHING)
                .price(11.11f)
                .manufacturer("test manufacturer")
                .supplier("test supplier")
                .build();

        product2 = Product.builder()
                .sku("000002")
                .name("name test 2")
                .description("this is product 2")
                .category(ELECTRONICS)
                .price(22.22f)
                .manufacturer("test manufacturer 2")
                .supplier("test supplier 2")
                .build();

         productResponse1 = ProductResponse.builder()
                .sku(product1.getSku())
                .name(product1.getName())
                .description(product1.getDescription())
                .category(product1.getCategory())
                .price(product1.getPrice())
                .build();

         productResponse2 = ProductResponse.builder()
                .sku(product2.getSku())
                .name(product2.getName())
                .description(product2.getDescription())
                .category(product2.getCategory())
                .price(product2.getPrice())
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    // methods testing

    @Test
    void greetingsTest() throws Exception{
        mockMvc.perform(get("/api/v1/products/test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(equalTo("Hello from Products DB!!!")));
    }



    @Test
    void getAllProductsTest()  throws Exception{
        //given


        List<ProductResponse> productResponseList = new ArrayList<ProductResponse>();
        productResponseList.add(productResponse1);
        productResponseList.add(productResponse2);
        given(productsServiceMock.getAllProducts()).willReturn(productResponseList);

        //when
        ResultActions response = mockMvc.perform(get("/api/v1/products/getAllProducts"));

        //then

        response.andExpect(status().isOk()).andDo(print()).andExpect(MockMvcResultMatchers.jsonPath("$.size()",is(productResponseList.size())));

    }

    @Test
    void addNewProductsTest() throws Exception {

        //given

         given(productsServiceMock.saveProducts(any(ProductsRequest.class))).willReturn(productResponse1);

        //when

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products/addProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productRequest1)))

        //then

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sku").value(productResponse1.getSku()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(productRequest1.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(productResponse1.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value(productResponse1.getCategory().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(productResponse1.getPrice()));
    }

    @Test
    void updateProductTest() throws Exception {

        /*
        // given

        int id= 1;
        ProductResponse updatedProductResponse1 = ProductResponse.builder()
                .sku("0000222")
                .name("UPDATED name test 2")
                .description("this is UPDATED product 2")
                .category(CLOTHING)
                .price(222.22f)
                .build();

        given(productsServiceMock.updateProduct(1, productRequest1)).willReturn(updatedProductResponse1);

        // when
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/updateProduct/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)  // Set Accept header
                        .content(objectMapper.writeValueAsString(updatedProductResponse1))
                )

                // then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sku").value("0000222"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("UPDATED name test 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("this is UPDATED product 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value(CLOTHING))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(222.22f));

         */

        ProductsRequest productsRequest = new ProductsRequest();
        productsRequest.setSku("000001");
        productsRequest.setName("name test 1");
        productsRequest.setDescription("this is product 1");
        productsRequest.setCategory(CLOTHING);
        productsRequest.setPrice(11.11f);
        productsRequest.setManufacturer("test manufacturer");
        productsRequest.setSupplier("test supplier");

        mockMvc.perform(put("/api/v1/products/updateProduct/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)  // Set Accept header
                        .content(new ObjectMapper().writeValueAsString(productsRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sku").value("0000222"))
                .andExpect(content().string(equalTo("Product updated successfully")));

    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void confirmProductBySku() {
    }
}