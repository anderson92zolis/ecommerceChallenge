package org.customerMicroservices.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest(CustomerController.class)
class CustomerController_Test {
    //region VARIABLES
    @Autowired
    private WebTestClient webTestClient;

    //endregion VARIABLES


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
    void getAll() {
        //region VARIABLES


        //endregion VARIABLES


        //region TEST INITIALIZATION


        //endregion TEST INITIALIZATION


        //region TEST


        //endregion TEST

    }

    //endregion TESTS

}

