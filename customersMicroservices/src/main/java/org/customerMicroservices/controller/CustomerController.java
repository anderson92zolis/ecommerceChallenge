package org.customerMicroservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "api/v1/customers")
@Tag(name = "eCommerce", description = "this controller contains the methods to work with the customers")
public class CustomerController {
    //region VARIABLES
    private static Logger log = LoggerFactory.getLogger(CustomerController.class);

    //endregion VARIABLES


    //region ENDPOINTS
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/test")
    public String test() {
        log.info("** Saludos desde el microservicio de customers **");

        return "Hello from CUSTOMER!!!";
    }

    //endregion ENDPOINTS

}
