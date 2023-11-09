package org.ecommerceChallenge.stockMicro.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerceChallenge.stockMicro.dto.StockRequest;
import org.ecommerceChallenge.stockMicro.dto.StockResponse;
import org.ecommerceChallenge.stockMicro.entity.Stock;
import org.ecommerceChallenge.stockMicro.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StockService {

    private final StockRepository stockRepository;

    public void saveProducts(StockRequest stockRequest){

        var stock = Stock.builder()
                .sku(stockRequest.getSku())
                .name(stockRequest.getName())
                .quantity(stockRequest.getQuantity())
                .build();

        this.stockRepository.save(stock);

        log.info("Product added to stock: {}", stock);

    }
    public List<StockResponse> getAllStock(){
        var products = stockRepository.findAll();

        log.info("stock products to show: {}", stockRepository);

        return products.stream().map(this::mapToProductResponse).toList();
    }

    private StockResponse mapToProductResponse(Stock stock) {
        return StockResponse.builder()
                .stockId(stock.getStockId())
                .sku(stock.getSku())
                .name(stock.getName())
                .quantity(stock.getQuantity())
                .localDatetime(stock.getLocalDatetime())
                .build();
    }




}
