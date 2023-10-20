package org.ecommerce.products.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.products.dto.ProductResponse;
import org.ecommerce.products.dto.ProductsDto;
import org.ecommerce.products.entity.Product;
import org.ecommerce.products.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductsService {

    private final ProductsRepository productsRepository;

    public void saveProducts(ProductsDto productRequestDto){

        var product = Product.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .category(productRequestDto.getCategory())
                .price(productRequestDto.getPrice())
                .manufacturer(productRequestDto.getManufacturer())
                .supplier(productRequestDto.getSupplier())
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
                .price(product.getPrice())
                .supplier(product.getSupplier())
                .build();
    }




}
