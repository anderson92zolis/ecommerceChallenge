package org.customerMicroservices.controller;

import org.customerMicroservices.documents.CustomerDocument;
import org.customerMicroservices.service.CustomersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/customers")
@Tag(name = "eCommerce", description = "this controller contains the methods to work with the customers")
public class CustomerController {
    //region VARIABLES
    private static Logger log = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomersService customersService;

    //endregion VARIABLES


    //region ENDPOINTS
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/test")
    public String test() {
        log.info("** Saludos desde el microservicio de customers **");

        return "Hello from CUSTOMER!!!";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<CustomerDocument>> getAll(){
        return (ResponseEntity<List<CustomerDocument>>) customersService.getAllCustomers();
    }




    //endregion ENDPOINTS

}
