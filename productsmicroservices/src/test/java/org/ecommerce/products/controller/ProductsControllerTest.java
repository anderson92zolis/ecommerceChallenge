package org.ecommerce.products.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ecommerce.products.dto.ProductResponse;
import org.ecommerce.products.dto.ProductsRequest;
import org.ecommerce.products.service.ProductsService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.ecommerce.products.entity.Category.CLOTHING;
import static org.ecommerce.products.entity.Category.ELECTRONICS;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

    private ProductResponse productResponse1;
    private ProductResponse productResponse2;

    private ProductsRequest productRequest1;



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


         productResponse1 = ProductResponse.builder()
                 .sku("000001")
                 .name("name test 1")
                 .description("this is product 1")
                 .category(CLOTHING)
                 .price(11.11f)
                .build();

         productResponse2 = ProductResponse.builder()
                 .sku("000002")
                 .name("name test 2")
                 .description("this is product 2")
                 .category(ELECTRONICS)
                 .price(22.22f)
                .build();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    @DisplayName("greeting test")
    void greetingsTest() throws Exception{

        //when
        mockMvc.perform(get("/api/v1/products/test")
                        .contentType(MediaType.APPLICATION_JSON))

        //then
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(equalTo("Hello from Products DB!!!")));
    }

    @Test
    @DisplayName("get product by Id test")
    void getProductByIdTest()  throws Exception{

        //given

        int productId= 1;
        given(productsServiceMock.getOneProductById(productId)).willReturn(productResponse1);

        //when

        mockMvc.perform(get("/api/v1/products/getOneProduct/{productId}", productId) )

                //then

                .andExpect(status().isOk()).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.sku").value(productResponse1.getSku()))
                .andExpect(jsonPath("$.name").value(productRequest1.getName()))
                .andExpect(jsonPath("$.description").value(productResponse1.getDescription()))
                .andExpect(jsonPath("$.category").value(productResponse1.getCategory().toString()))
                .andExpect(jsonPath("$.price").value(productResponse1.getPrice()));;

    }


    @Test
    @DisplayName("get all products test")
    void getAllProductsTest()  throws Exception{

        //given

        List<ProductResponse> productResponseList = new ArrayList<ProductResponse>();
        productResponseList.add(productResponse1);
        productResponseList.add(productResponse2);
        given(productsServiceMock.getAllProducts()).willReturn(productResponseList);

        //when

        mockMvc.perform(get("/api/v1/products/getAllProducts"))

        //then

                .andExpect(status().isOk()).andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",is(productResponseList.size())));

    }

    @Test
    @DisplayName("add product test")
    void addNewProductsTest() throws Exception {

        //given

         given(productsServiceMock.saveProducts(any(ProductsRequest.class))).willReturn(productResponse1);

        //when

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products/addProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(productRequest1)))

        //then

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.sku").value(productResponse1.getSku()))
                .andExpect(jsonPath("$.name").value(productRequest1.getName()))
                .andExpect(jsonPath("$.description").value(productResponse1.getDescription()))
                .andExpect(jsonPath("$.category").value(productResponse1.getCategory().toString()))
                .andExpect(jsonPath("$.price").value(productResponse1.getPrice()));
    }

    @Test
    @DisplayName("update product test")
    void updateProductTest() throws Exception {


        // link  https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/


        // given

        int id= 1;
        ProductResponse updatedProductResponse1 = ProductResponse.builder()
                .sku("0000222")
                .name("UPDATED name test 2")
                .description("this is UPDATED product 2")
                .category(CLOTHING)
                .price(222.22f)
                .build();

        given(productsServiceMock.updateProduct(eq(id), any(ProductsRequest.class))).willReturn(updatedProductResponse1);

        // when
        mockMvc.perform(put("/api/v1/products/updateProduct/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedProductResponse1))
                        .accept(MediaType.APPLICATION_JSON)  // Set Accept header
                )

        // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sku").value(updatedProductResponse1.getSku()))
                .andExpect(jsonPath("$.name").value(updatedProductResponse1.getName()))
                .andExpect(jsonPath("$.description").value(updatedProductResponse1.getDescription()))
                .andExpect(jsonPath("$.category").value(updatedProductResponse1.getCategory().toString()))
                .andExpect(jsonPath("$.price").value(updatedProductResponse1.getPrice()));

    }

    @Test
    @DisplayName("delete product test")
    void deleteProduct() throws Exception {

        // when

        mockMvc.perform(delete("/api/v1/products/deleteProduct/{id}", 1) )

        // then
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("get product test by sku")
    void getProductById() throws Exception {

        //given

        String sku= "000001";
        given(productsServiceMock.findBySku(sku)).willReturn(productResponse1);

        //when
        mockMvc.perform(get("/api/v1/products/getProduct/{sku}",sku))

        //then

        .andExpect(status().isOk()).andDo(print())
        .andExpect(jsonPath("$.sku").value(productResponse1.getSku()))
        .andExpect(jsonPath("$.name").value(productResponse1.getName()))
        .andExpect(jsonPath("$.description").value(productResponse1.getDescription()))
        .andExpect(jsonPath("$.category").value(productResponse1.getCategory().toString()))
        .andExpect(jsonPath("$.price").value(productResponse1.getPrice()));

    }

    @Test
    @DisplayName("product exits test by sku")
    void confirmProductBySku() throws Exception{

        //given
        String sku= "000001";
        Boolean mockResult=true;
        given(productsServiceMock.confirmProductBySku(sku)).willReturn(mockResult);

        //when
        mockMvc.perform(get("/api/v1/products/confirmProduct/{sku}",sku))

        //then

        .andExpect(status().isOk()).andDo(print())
        .andExpect(content().string(String.valueOf(mockResult)));

    }

    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}