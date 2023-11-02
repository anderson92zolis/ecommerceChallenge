package org.ecommerce.products.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.products.dto.ProductResponse;
import org.ecommerce.products.dto.ProductsRequest;
import org.ecommerce.products.service.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "thymeleaf")
public class ProductsControllerThymeleaf {

    private final ProductsService productsService;

    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        // Your logic here
        return "add-product";
    }

    @GetMapping(value = "/getAllProducts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productsService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @PostMapping(value = "/addProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewProducts(@Valid ProductsRequest productsRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-Product";
        }
        productsService.saveProducts(productsRequest);
        return "redirect:/index";
    }



}
