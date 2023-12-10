package org.clientsMicroservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "api/v1/clients")
@Tag(name = "eCommerce", description = "this controller contains the methods to work with the clients")
public class ClientController {
    //region VARIABLES
    private static Logger log = LoggerFactory.getLogger(ClientController.class);

    //endregion VARIABLES


    //region CONSTRUCTOR

    //endregion CONSTRUCTOR


    //region ENDPOINTS
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/test")
    public String test() {
        log.info("** Saludos desde el microservicio de clientes **");

        return "Hello from CLIENTS!!!";
    }

    //endregion ENDPOINTS


}
