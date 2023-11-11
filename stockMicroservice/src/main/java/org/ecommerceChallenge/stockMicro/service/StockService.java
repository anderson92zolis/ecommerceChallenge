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

    public void saveStock(StockRequest stockRequest){

        var stock = Stock.builder()
                .productId(stockRequest.getProductId())
                .stockId(stockRequest.getProductId())
                .sku(stockRequest.getSku())
                .name(stockRequest.getName())
                .quantity(stockRequest.getQuantity())
                .build();

        this.stockRepository.save(stock);

        log.info("Product added to stock: {}", stock);

    }
    public List<StockResponse> getAllStock(){
        var stocks = stockRepository.findAll();

        log.info("stock products to show: {}", stockRepository);

        return stocks.stream().map(this::mapToProductResponse).toList();
    }


    public String verifyProductIdByQuantity(int productId, int orderQuantity) {
        Stock stock = stockRepository.findByProductId(productId);
        String verifyQuantity= "accepted";

        if (stock.getProductId()<=orderQuantity) {
            verifyQuantity= "false";
        }

        return verifyQuantity;
    }

    private StockResponse mapToProductResponse(Stock stock) {
        return StockResponse.builder()
                .productId(stock.getProductId())
                .stockId(stock.getStockId())
                .sku(stock.getSku())
                .productId(stock.getProductId())
                .name(stock.getName())
                .quantity(stock.getQuantity())
                .localDatetime(stock.getLocalDatetime())
                .build();
    }
}
