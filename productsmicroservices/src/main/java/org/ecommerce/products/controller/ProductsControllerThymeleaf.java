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


    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("allProductList", productsService.getAllProducts());
        return "index";
    }
    @GetMapping("/addnewProduct")
    public String addNewProduct(Model model) {
        ProductsRequest productsRequest = new ProductsRequest();
        model.addAttribute("productsRequest", productsRequest);
        return "addProduct";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("productsRequest") ProductsRequest productsRequest) {
        productsService.saveProducts(productsRequest);
        return "redirect:/";
    }

    @GetMapping(value = "/getAllProducts")
    @ResponseStatus(HttpStatus.OK)
    public String getAllProducts(Model model) {
        List<ProductResponse> products = productsService.getAllProducts();
        model.addAttribute("products", products);
        return "index";
    }






}
