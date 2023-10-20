package org.ecommerce.products.controller;

import lombok.AllArgsConstructor;
import org.ecommerce.products.entity.Products;
import org.ecommerce.products.service.ProductsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/products")

public class ProductsController {

    private final ProductsService productsService;


    @GetMapping(value = "/getAllProduts")
    public List<Products> getAllProducts(){
        return productsService.getAllProducts();
    }

}
