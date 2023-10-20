package org.ecommerce.products.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.products.dto.ProductResponse;
import org.ecommerce.products.dto.ProductsDto;
import org.ecommerce.products.entity.Product;
import org.ecommerce.products.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "api/v1/products")

public class ProductsController {

    private final ProductsService productsService;


    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(value = "/test")
    public String test() {
        log.info("** Saludos desde el logger **");


        return "Hello from Products DB!!!";
    }

    @GetMapping(value = "/getAllProducts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productsService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @PostMapping(value = "/addProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewProducts(@RequestBody ProductsDto productsDtoRequest){
        productsService.saveProducts(productsDtoRequest);
    }



}
