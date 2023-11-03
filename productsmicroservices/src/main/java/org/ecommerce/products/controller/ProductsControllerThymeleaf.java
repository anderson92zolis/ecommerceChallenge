package org.ecommerce.products.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.products.dto.ProductResponse;
import org.ecommerce.products.dto.ProductsRequest;
import org.ecommerce.products.service.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // always verify this Controler vs RestController
@AllArgsConstructor
@Slf4j
public class ProductsControllerThymeleaf {

    private final ProductsService productsService;

    @GetMapping("/")
    public String viewHomePage(ProductsRequest productsRequest) {
        return "index";
    }

    @GetMapping("/addnewProduct")
    public String addNewProduct(Model model) {
        ProductsRequest productsRequest = new ProductsRequest();
        model.addAttribute("productsRequest", productsRequest);
        return "addProduct"; // here the page where is going to go
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@Valid @ModelAttribute("productsRequest") ProductsRequest productsRequest, BindingResult result) {
        if (result.hasErrors()) {
            return "addProduct"; // redirection to the addProduct.html again
        }
        productsService.saveProducts(productsRequest);
        return "redirect:/"; // redirection to the index.html again
    }

    @GetMapping("/getAllProducts")
    public String getAllProducts(Model model) {
        List<ProductResponse> productsResponse   = productsService.getAllProducts();
        model.addAttribute("productsResponse", productsResponse);
        return "seeAllProduct";
    }
}
