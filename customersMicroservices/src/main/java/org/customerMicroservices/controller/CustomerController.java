package org.customerMicroservices.controller;

import org.customerMicroservices.documents.CustomerDocument;
import org.customerMicroservices.dto.CustomerDTO;
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

    @PostMapping(value= "/create")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO){
        return customersService.createCustomer(customerDTO);
    }

    @DeleteMapping(value ="/delete/{uuid}")
    public ResponseEntity<Boolean> delete(@PathVariable("uuid") String uuid){
        return customersService.delete(uuid);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<CustomerDTO>> getAll(){
        return customersService.getAllCustomers();
    }

    @GetMapping(value = "/get/{uuid}")
    public ResponseEntity<CustomerDTO> getOne(@PathVariable("uuid") String uuid){
        return customersService.getOne(uuid);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customerDTO){
        return customersService.update(customerDTO);
    }

    // This endpoint is connected with Order ms
    @GetMapping(value = "/getForOrder/{uuid}")
    public CustomerDTO getForOrder (@PathVariable("uuid") String uuid){
        return customersService.getForOrder(uuid);
    }



    //endregion ENDPOINTS

}
