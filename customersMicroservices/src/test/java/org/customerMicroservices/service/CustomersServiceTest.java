package org.customerMicroservices.service;

import org.bson.types.ObjectId;
import org.customerMicroservices.documents.CustomerDocument;
import org.customerMicroservices.dto.AddressDTO;
import org.customerMicroservices.dto.CustomerDTO;
import org.customerMicroservices.helpers.ConverterDocsAndDtos;
import org.customerMicroservices.repository.CustomerRepository;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
// Needed to @Order() in test method to work fine.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// Needed to do @BeforeAll not static.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomersServiceTest {
    //region VARIABLES
    private List<CustomerDocument> customersList;

    private UUID uuid1, uuid2;
    private ArrayList<Integer> ordersList1;
    private AddressDTO address1;
    private CustomerDTO customer1, customer2;

    @Autowired
    private WebTestClient webTestClient;
    // Mock variables
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerRepository customerRepositoryMock;
    @InjectMocks
    private CustomersService customersService;

    //endregion VARIABLES


    //region CONSTRUCTOR
    @BeforeAll
    void beforeAll() {
        //region ACTIONS
        // INITIALIZATIONS
        customersList = new ArrayList<>();

        // SETUP MOCK VALUES
        MockitoAnnotations.openMocks(this);

        // PREPARE ANSWERS VALUES
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
                .name("customer12")
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

        customersList.add(new CustomerDocument(
                null, uuid1, "sandrapass", "Sandra", "26721434B",
                address1, ordersList1));
        customersList.add(new CustomerDocument(
                null, UUID.randomUUID(), "laiapass", "Laia", "89003777X",
                address1, ordersList1));
        customersList.add(new CustomerDocument(
                null, UUID.randomUUID(), "carlespass", "Carles", "07190088N",
                address1, ordersList1));

        //endregion ACTIONS

    }

    //endregion CONSTRUCTOR


    //region TESTS
    @Test
    @Order(1)
    /**
     * Correct test, create customer without problems.
     */
    void createCustomer() {
        //region VARIABLES
        CustomerDocument customerDoc1;
        ResponseEntity<CustomerDTO> responseEntity;

        //endregion VARIABLES


        //region TEST INITIALIZATION
        customerDoc1 = ConverterDocsAndDtos.dtoToDoc(customer1);
        customerDoc1.set_id(new ObjectId("61f8bc12b674ad0c1b1ddbec"));
        // Prepare mock answers
        ArgumentCaptor<CustomerDocument> captor = ArgumentCaptor.forClass(CustomerDocument.class);
        when(customerRepositoryMock
                .save(captor.capture()))
                .thenAnswer(invocation -> customerDoc1);
        when(customerRepositoryMock.existByName(customerDoc1.getName())).thenReturn(false);

        //endregion TEST INITIALIZATION


        //region TEST
        // Correct test
        responseEntity = customersService.createCustomer(customer1);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        //endregion TEST

    }

    @Test
    @Order(2)
    /**
     * Check wrong 2, pass CustomerDTO = null and customer's name exist on DDBB.
     */
    void createCustomer2() {
        //region VARIABLES
        CustomerDocument customerDoc1, customerDoc2;
        ResponseEntity<CustomerDTO> responseEntity;

        //endregion VARIABLES


        //region TEST INITIALIZATION
        customerDoc1 = ConverterDocsAndDtos.dtoToDoc(customer1);
        // Prepare mock answers
        ArgumentCaptor<CustomerDocument> captor = ArgumentCaptor.forClass(CustomerDocument.class);
        when(customerRepositoryMock
                .save(captor.capture()))
                .thenAnswer(invocation -> customerDoc1);
        when(customerRepositoryMock.existByName(customerDoc1.getName())).thenReturn(false);

        //endregion TEST INITIALIZATION


        //region TEST
        // Wrong test 1: Pass null CustomerDTO value
        responseEntity = customersService.createCustomer(null);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        // Wrong test 2: Customer's name exist in DDBB
        when(customerRepositoryMock.existByName(customerDoc1.getName())).thenReturn(true);
        responseEntity = customersService.createCustomer(customer1);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());

        //endregion TEST

    }

    @Test
    @Order(3)
    /**
     * Check Repository return error.
     */
    void createCustomer3() {
        //region VARIABLES
        CustomerDocument customerDoc1;
        ResponseEntity<CustomerDTO> responseEntity;

        //endregion VARIABLES


        //region TEST INITIALIZATION
        customerDoc1 = ConverterDocsAndDtos.dtoToDoc(customer1);
        // Prepare mock answers
        ArgumentCaptor<CustomerDocument> captor = ArgumentCaptor.forClass(CustomerDocument.class);
        when(customerRepositoryMock
                .save(captor.capture()))
                .thenAnswer(invocation -> customerDoc1);
        when(customerRepositoryMock.existByName(customerDoc1.getName())).thenReturn(false);

        //endregion TEST INITIALIZATION


        //region TEST
        // Wrong: Error on created customer on DDBB
        responseEntity = customersService.createCustomer(customer1);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

        //endregion TEST

    }

    @Test
    @Order(4)
    /**
     * Check Exception.
     */
    void createCustomer4() {
        //region VARIABLES
        CustomerDocument customerDoc1;
        ResponseEntity<CustomerDTO> responseEntity;

        //endregion VARIABLES


        //region TEST INITIALIZATION
        customerDoc1 = ConverterDocsAndDtos.dtoToDoc(customer1);
        // Prepare mock answers
        ArgumentCaptor<CustomerDocument> captor = ArgumentCaptor.forClass(CustomerDocument.class);
        when(customerRepositoryMock
                .save(captor.capture()))
                .thenAnswer(invocation -> customerDoc1);
        when(customerRepositoryMock.existByName(customerDoc1.getName())).thenThrow(new RuntimeException());

        //endregion TEST INITIALIZATION


        //region TEST
        // Wrong: Error on created customer on DDBB
        responseEntity = customersService.createCustomer(customer1);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

        //endregion TEST

    }

    @Test
    void delete() {
    }

    @Test
    @Order(5)
    void getAllCustomers() {
        //region VARIABLES
        ResponseEntity<List<CustomerDTO>> responseEntity;

        //endregion VARIABLES


        //region TEST INITIALIZATION
        // Mock answers
        when(customerRepositoryMock.findAll()).thenReturn(customersList);

        //endregion TEST INITIALIZATION


        //region TEST
        // Correct answer
        responseEntity = customersService.getAllCustomers();
        assertNotNull(responseEntity);
        assertEquals(3, responseEntity.getBody().size());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Wrong 1: No customers on ddbb
        when(customerRepositoryMock.findAll()).thenReturn(null);
        responseEntity = customersService.getAllCustomers();
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        //endregion TEST

    }

    @Test
    void getOne() {
        //region VARIABLES


        //endregion VARIABLES


        //region TEST INITIALIZATION


        //endregion TEST INITIALIZATION


        //region TEST


        //endregion TEST

    }

    @Test
    void update() {
    }

    @Test
    void testCreateCustomer() {
    }

    @Test
    void testDelete() {
    }

    @Test
    void testGetAllCustomers() {
    }

    @Test
    void testGetOne() {
    }

    @Test
    void testUpdate() {
    }


    //endregion TESTS

}