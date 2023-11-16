package org.ecommerce.products.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.products.dto.ProductResponse;
import org.ecommerce.products.dto.ProductsRequest;
import org.ecommerce.products.entity.Product;
import org.ecommerce.products.exception.ProductNotFound;
import org.ecommerce.products.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductsService {

    private final ProductsRepository productsRepository;

    public void saveProducts(ProductsRequest productsRequest){

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

    }
    public List<ProductResponse> getAllProducts(){
        var products = productsRepository.findAll();

        log.info("Products to show: {}", productsRepository);

        return products.stream().map(this::mapToProductResponse).toList();

    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
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
}
