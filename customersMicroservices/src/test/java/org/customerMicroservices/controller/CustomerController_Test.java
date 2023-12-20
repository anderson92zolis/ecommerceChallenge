package org.customerMicroservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.customerMicroservices.documents.CustomerDocument;
import org.customerMicroservices.service.CustomersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.kafka.listener.ConsumerAwareRebalanceListener.LOGGER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


////* @WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
class CustomerController_Test {
    //region VARIABLES
    List<CustomerDocument> customersList;

    @Autowired
    private WebTestClient webTestClient;
    // Mock variables
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomersService customersServiceMock;
    @InjectMocks
    private CustomerController customerController;

    //endregion VARIABLES


    //region CONSTRUCTOR
    @BeforeEach
    void setUp(){
        //region ACTIONS
        try {
            // INITIALIZATIONS
            MockitoAnnotations.openMocks(this);
            customersList = new ArrayList<>();

            // SETUP MOCK VALUES
            customersList.add(new CustomerDocument(null, UUID.randomUUID(), "Sandra"));
            customersList.add(new CustomerDocument(null, UUID.randomUUID(), "Laia"));
            customersList.add(new CustomerDocument(null, UUID.randomUUID(), "Carles"));

            // SETUP MOCK INSTRUCTIONS
            when(customersServiceMock.getAllCustomers()).thenReturn(customersList);

        } catch (Exception ex) {
            LOGGER.info(this.getClass().getSimpleName() + ": ERROR =>" + ex.getMessage());
        }

        //endregion ACTIONS


    }

    //endregion CONSTRUCTOR


    //region TESTS
    @Test
    void test1() {
        webTestClient.get()
                .uri("/api/v1/customers/test")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Hello from CUSTOMER!!!");
    }

    @Test
    void getAll() throws Exception{
        //region TEST
        mockMvc.perform(get("/api/v1/customers/getAll").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value(customersList.get(0).getName()))
                .andExpect(jsonPath("[1].uuid").value(customersList.get(1).getUuid().toString()))
                .andExpect(content().json(asJsonString(customersList)));

        //endregion TEST

    }

    //endregion TESTS


    //region METHODS: Privates
    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //endregion METHODS: Privates

}

