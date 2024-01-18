package org.ecommerce.products.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.products.entity.Product;
import org.ecommerce.products.repository.ProductsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ProductsRepository productsRepository;

    public void run(String... args) throws Exception {
        log.info("Loading mocked data...");
        if(productsRepository.findAll().isEmpty()){
            productsRepository.saveAll(
                    List.of(
                            Product.builder().productId(1).sku("000001").name("camera").description("camera").category(null).price(15).manufacturer("manufacturer").supplier("supplier").build(),
                            Product.builder().productId(2).sku("000002").name("pot").description("pot").category(null).price(25).manufacturer("manufacturer").supplier("supplier").build()
                    )
            );
        }
        log.info("Data loaded.");
    }
}
