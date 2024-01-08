package org.customerMicroservices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.customerMicroservices.dto.CustomerDTO;
import org.customerMicroservices.service.CustomersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.kafka.listener.ConsumerAwareRebalanceListener.LOGGER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


////* @WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
    private CustomerDTO customerDTONew;
    private List<CustomerDTO> customersList;

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
    void setUp() {
        //region VARIABLES
        UUID uuid1;

        //endregion VARIABLES

        //region ACTIONS
        try {
            // INITIALIZATIONS
            uuid1 = UUID.randomUUID();
            //region VARIABLES
            HttpHeaders httpHeaders = new HttpHeaders();
            MockitoAnnotations.openMocks(this);
            customersList = new ArrayList<>();

            // SETUP MOCK VALUES
            customersList.add(new CustomerDTO(
                    uuid1, "sandrapass", "Sandra", "26721434B", null, null));
            customersList.add(new CustomerDTO(
                    UUID.randomUUID(), "laiapass", "Laia", "89003777X", null, null));
            customersList.add(new CustomerDTO(
                    UUID.randomUUID(), "carlespass", "Carles", "07190088N", null, null));
            // For endpoint Create
            customerDTONew = new CustomerDTO(
                    UUID.randomUUID(), "pacopass", "Paco", "25262281R", null, null);

            // SETUP MOCK INSTRUCTIONS
            // Create
            when(customersServiceMock.createCustomer(customerDTONew)).thenReturn(
                    new ResponseEntity<>(customerDTONew, httpHeaders, HttpStatus.CREATED));
            // Delete
            when(customersServiceMock.delete(customerDTONew.getUuid().toString())).thenReturn(
                    new ResponseEntity<>(true, httpHeaders, HttpStatus.OK));
            // Getall
            when(customersServiceMock.getAllCustomers()).thenReturn(
                    new ResponseEntity<>(customersList, httpHeaders, HttpStatus.CREATED));
            // GetOne
            when(customersServiceMock.getOne(uuid1.toString())).thenReturn(
                    new ResponseEntity<>(customersList.get(0), httpHeaders, HttpStatus.CREATED));
            // Update
            when(customersServiceMock.update(customersList.get(0))).thenReturn(
                    new ResponseEntity<>(customersList.get(0), httpHeaders, HttpStatus.CREATED));

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
    void createCustomer() throws Exception {
        //region TEST
        mockMvc.perform(post("/api/v1/customers/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDTONew)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name").value(customerDTONew.getName()))
                .andExpect(jsonPath("uuid").value(customerDTONew.getUuid().toString()))
                .andExpect(content().json(asJsonString(customerDTONew)));
    }

    @Test
    void delete() throws Exception {
        //region TEST
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customers/delete/" + customerDTONew.getUuid().toString()))
                .andExpect(status().isOk());

    }

    @Test
    void getAll() throws Exception {
        //region TEST
        mockMvc.perform(get("/api/v1/customers/getAll").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("[0].name").value(customersList.get(0).getName()))
                .andExpect(jsonPath("[1].uuid").value(customersList.get(1).getUuid().toString()))
                .andExpect(content().json(asJsonString(customersList)));

        //endregion TEST

    }

    @Test
    void getOne() throws Exception {
        //region TEST
        mockMvc.perform(get("/api/v1/customers/get/" + customersList.get(0).getUuid().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name").value(customersList.get(0).getName()))
                .andExpect(jsonPath("uuid").value(customersList.get(0).getUuid().toString()))
                .andExpect(content().json(asJsonString(customersList.get(0))));

        //endregion TEST

    }

    @Test
    void update() throws Exception {
        //region TEST
        mockMvc.perform(put("/api/v1/customers/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customersList.get(0))))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("name").value(customersList.get(0).getName()))
                .andExpect(jsonPath("uuid").value(customersList.get(0).getUuid().toString()))
                .andExpect(content().json(asJsonString(customersList.get(0))));

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

