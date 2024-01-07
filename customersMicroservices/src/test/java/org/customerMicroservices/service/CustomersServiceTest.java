package org.customerMicroservices.service;

import org.customerMicroservices.dto.AddressDTO;
import org.customerMicroservices.dto.CustomerDTO;
import org.customerMicroservices.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static javax.print.attribute.standard.ReferenceUriSchemesSupported.HTTPS;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CustomersServiceTest {
    //region VARIABLES
    private UUID uuid1, uuid2;
    private ArrayList<Integer> ordersList1;
    private AddressDTO address1;
    private CustomerDTO customer1, customer2;

    @Autowired
    private WebTestClient webTestClient;
    // Mock variables
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private CustomersService customersService;
    @MockBean
    private CustomerRepository customerRepositoryMock;

    //endregion VARIABLES


    //region CONSTRUCTOR
    @BeforeEach
    void setup() {
        //region ACTIONS
        // SETUP MOCK VALUES
        uuid1 = UUID.randomUUID();
        uuid2 = UUID.randomUUID();
        ordersList1 = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 6, 7, 8, 9));
        address1 = AddressDTO.builder()
                .street("street")
                .city("city")
                .postalCode(8080)
                .country("country")
                .build();
        customer1 = CustomerDTO.builder()
                .uuid(uuid1)
                .pass("customer1Password")
                .name("customer1")
                .dni("78124592D")
                .address(address1)
                .ordersList(ordersList1).build();
        customer2 = CustomerDTO.builder()
                .uuid(uuid2)
                .pass("customer2Password")
                .name("customer2")
                .dni("30801629Y")
                .address(address1)
                .ordersList(ordersList1).build();

        //endregion ACTIONS

    }

    //endregion CONSTRUCTOR


    //region TESTS
    @Test
    void createCustomer() {
        //region VARIABLES
        ResponseEntity<CustomerDTO> responseEntity;

        //endregion VARIABLES


        //region TEST INITIALIZATION
        responseEntity = customersService.createCustomer(customer1);

        //endregion TEST INITIALIZATION


        //region TEST
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        //endregion TEST

    }

    @Test
    void delete() {
    }

    @Test
    void getAllCustomers() {
    }

    @Test
    void getOne() {
    }

    @Test
    void update() {
    }


    //endregion TESTS

}