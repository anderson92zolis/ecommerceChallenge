package org.ecommerceChallenge.stockMicro.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerceChallenge.stockMicro.entity.Stock;
import org.ecommerceChallenge.stockMicro.repository.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final StockRepository stockRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading data...");
        if (stockRepository.findAll().isEmpty()) {
            stockRepository.saveAll(
                    List.of(
                            Stock.builder().productId(1).sku("000001").name("camera").quantity(1).build(),
                            Stock.builder().productId(2).sku("000002").name("pot").quantity(20).build(),
                            Stock.builder().productId(3).sku("000003").name("IA book").quantity(30).build(),
                            Stock.builder().productId(4).sku("000004").name("crypto").quantity(40).build()
                    )
            );
        }
        log.info("Data loaded.");
    }
}