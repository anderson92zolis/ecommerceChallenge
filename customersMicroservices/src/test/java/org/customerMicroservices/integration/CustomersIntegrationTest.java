package org.customerMicroservices.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.customerMicroservices.dto.AddressDTO;
import org.customerMicroservices.dto.CustomerDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
// Needed to @Order() in test method to work fine.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// Needed to do @BeforeAll not static.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomersIntegrationTest {
    private CustomerDTO customer1;

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private ObjectMapper objectMapper;

    //endregion VARIABLES


    //region CONSTRUCTOR
    @BeforeAll
    public void beforeAll(){
        //region VARIABLES
        ArrayList<Integer> ordersList1;
        AddressDTO address1;

        //endregion VARIABLES


        //region ACTIONS
        ordersList1 = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 6, 7, 8, 9));
        address1 = AddressDTO.builder()
                .street("street")
                .city("city")
                .postalCode(8080)
                .country("country")
                .build();
        customer1 = CustomerDTO.builder()
                .uuid(null)
                .pass("customer1Password")
                .name("customer1")
                .dni("78124592D")
                .address(address1)
                .ordersList(ordersList1).build();

        //endregion ACTIONS
    }

    @BeforeEach
    public void setup(){

    }

    //endregion CONSTRUCTOR


    //region TESTS
    @Test
    @Order(5)
    void deleteCustomerTest(){
        //region TEST
        try {
            webTestClient
                    .delete()
                    .uri("/api/v1/customers/delete/{uuid}", customer1.getUuid())
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(Boolean.class)
                    .consumeWith(response -> {
                        // GET BODY RESPONSE
                        boolean deleted = response.getResponseBody();
                        // DO A TESTS
                        assertTrue(deleted);
                    });
        } catch (Exception ex) {
            //todo control of exception
        }

        //endregion TEST

    }

    @Test
    @Order(1)
    void createCustomer() {
        //region TEST
        try {
            webTestClient
                    .post()
                    .uri("/api/v1/customers/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(objectMapper.writeValueAsString(customer1)))
                    .exchange()
                    .expectStatus().isCreated()
                    .expectBody(CustomerDTO.class)
                    .consumeWith(response -> {
                        // GET CUSTOMER CREATED
                        CustomerDTO createdCustomer = response.getResponseBody();
                        // DO A TESTS
                        assertNotNull(createdCustomer);
                        assertNotNull(createdCustomer.getUuid());
                        // SAVE TO GLOBAL VALUE
                        customer1.setUuid(createdCustomer.getUuid());
                    });
        } catch (Exception ex) {
            //todo control of exception
        }

        //endregion TEST

    }

    @Test
    @Order(2)
    void getAll() {
        //region TEST
        try {
            // INITIALIZATION
            webTestClient.get()
                    .uri("/api/v1/customers/getAll")
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(List.class)
                    .consumeWith(response -> {
                        List<CustomerDTO> customersList = response.getResponseBody();
                        assertNotNull(customersList);
                        assertNotEquals(0, customersList.size());
                    });

        } catch (Exception ex) {
            //todo control of exceptions
        }

        //endregion TEST

    }

    @Test
    @Order(3)
    void getOne() {
        //region TEST
        try {
            // INITIALIZATION
            webTestClient.get()
                    .uri("/api/v1/customers/get/{uuid}", customer1.getUuid())
                    .exchange()
                    .expectStatus().isCreated()
                    .expectBody(CustomerDTO.class)
                    .consumeWith(response -> {
                        CustomerDTO customersDTO = response.getResponseBody();
                        assertNotNull(customersDTO);
                        assertEquals(customer1.getName(), customersDTO.getName());
                    });

        } catch (Exception ex) {
            //todo control of exceptions
        }

        //endregion TEST
    }

    @Test
    @Order(4)
    void update() {
        //region TEST INITIALIZATION
        customer1.setName("CustomerModified");

        //endregion TEST INITIALIZATION


        //region TEST
        try {
            // INITIALIZATION
            webTestClient.put()
                    .uri("/api/v1/customers/update")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(objectMapper.writeValueAsString(customer1)))
                    .exchange()
                    .expectStatus().isCreated()
                    .expectBody(CustomerDTO.class)
                    .consumeWith(response -> {
                        // GET CUSTOMER MODIFIED
                        CustomerDTO customersDTO = response.getResponseBody();
                        assertNotNull(customersDTO);
                        assertEquals(customer1.getName(), customersDTO.getName());
                    });
        } catch (Exception ex) {
            //todo control of exceptions
        }

        //endregion TEST

    }

    //endregion TESTS
}
