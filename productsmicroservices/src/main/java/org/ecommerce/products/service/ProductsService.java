package org.ecommerce.products.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.products.dto.ProductResponse;
import org.ecommerce.products.dto.ProductsRequest;
import org.ecommerce.products.entity.Product;
import org.ecommerce.products.exception.ProductNotFound;
import org.ecommerce.products.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductsService {
    @Autowired
    private final ProductsRepository productsRepository;

    public ProductResponse saveProducts(ProductsRequest productsRequest){

        var product = Product.builder()
                .sku(productsRequest.getSku())
                .name(productsRequest.getName())
                .description(productsRequest.getDescription())
                .category(productsRequest.getCategory())
                .price(productsRequest.getPrice())
                .manufacturer(productsRequest.getManufacturer())
                .supplier(productsRequest.getSupplier())
                .build();

        productsRepository.save(product);

        log.info("Product added: {}", product);
        return mapToProductResponse(product);
    }

    public ProductResponse updateProduct(int id, ProductsRequest productsRequest){

        Product updatedProduct = productsRepository.findById(id).orElseThrow(()-> new ProductNotFound("THE PRODUCT DOES NOT EXISTS, WITH ID: "+id) );


        updatedProduct = Product.builder()
                .productId(id)
                .sku(productsRequest.getSku())
                .name(productsRequest.getName())
                .description(productsRequest.getDescription())
                .localDatetime(LocalDateTime.now())
                .category(productsRequest.getCategory())
                .price(productsRequest.getPrice())
                .manufacturer(productsRequest.getManufacturer())
                .supplier(productsRequest.getSupplier())
                .build();

        productsRepository.save(updatedProduct);

        log.info("Product updated: {}", updatedProduct);
        return mapToProductResponse(updatedProduct);

    }

    public void deleteProductById(int id) {
        if(productsRepository.existsById(id)){
            productsRepository.deleteById(id);
                    } else {
            throw new ProductNotFound("THE PRODUCT DOES NOT EXISTS, WITH ID: "+id);
        }
    }

    public List<ProductResponse> getAllProducts(){
        var products = productsRepository.findAll();

        log.info("Products to show: {}", productsRepository);

        return products.stream().map(this::mapToProductResponse).toList();

    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .build();
    }

    public ProductResponse findBySku(String sku) {

        Product productFound = productsRepository.getBySku(sku);
        if(productFound==null){
            throw new ProductNotFound("The product does not exists");
        }
        return mapToProductResponse(productFound);
    }

    public boolean confirmProductBySku(String sku) {

        boolean confirmProduct = false;
        Product productFound = productsRepository.getBySku(sku);
        if(productFound!=null){
            confirmProduct = true;
        }
        return confirmProduct;
    }

    public int suma(int a, int b){
        return a+b;
    }

}
