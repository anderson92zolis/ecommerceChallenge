package org.ecommerce.products.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.products.dto.ProductResponse;
import org.ecommerce.products.dto.ProductsRequest;
import org.ecommerce.products.service.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public String greetings() {
        log.info("** Saludos desde el logger **");

        return "Hello from Products DB!!!";
    }

    @GetMapping(value = "/getOneProduct/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponse> getOneProductById(@PathVariable("id") int id) {
        ProductResponse products = productsService.getOneProductById(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }



    @GetMapping(value = "/getAllProducts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productsService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping(value = "/addProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponse> addNewProducts(@RequestBody ProductsRequest productsRequest){
        return ResponseEntity.ok(productsService.saveProducts(productsRequest));
    }

    @PutMapping(value = "/updateProduct/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") int id, @RequestBody ProductsRequest productsRequest){
        return ResponseEntity.ok(productsService.updateProduct(id,productsRequest));
    }

    @DeleteMapping(value = "/deleteProduct/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id){
        productsService.deleteProductById(id);
        return ResponseEntity.ok("Product deleted correctly with ID: "+ id);
    }

    @GetMapping(value = "/getProduct/{sku}")
    public ResponseEntity<ProductResponse> getProductById (@PathVariable String sku){
        return ResponseEntity.ok(productsService.findBySku(sku));
    }

    @GetMapping(value = "/confirmProduct/{sku}")
    public boolean confirmProductBySku (@PathVariable String sku) {
        return productsService.confirmProductBySku (sku);
    }

}
