package org.ecommerceChallenge.stockMicro.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerceChallenge.stockMicro.dto.StockRequest;
import org.ecommerceChallenge.stockMicro.dto.StockResponse;
import org.ecommerceChallenge.stockMicro.entity.Stock;
import org.ecommerceChallenge.stockMicro.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class StockService {

    private final StockRepository stockRepository;

    public void saveStock(StockRequest stockRequest){

        var stock = Stock.builder()
                .productId(stockRequest.getProductId())
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


    // PART OF OPENFEIGN

    public String verifyProductIdByQuantity(int productId, int orderQuantity) {

        Optional<Stock> stock = stockRepository.findById(productId);

        System.out.println();

        System.out.println(stock);

        String verifyQuantity= "Accepted";

        if ( stock.isPresent() && orderQuantity > stock.get().getQuantity()) {
            verifyQuantity= "Not accepted";
        }

        return verifyQuantity;
    }

    private StockResponse mapToProductResponse(Stock stock) {
        return StockResponse.builder()
                .productId(stock.getProductId())
                .sku(stock.getSku())
                .productId(stock.getProductId())
                .name(stock.getName())
                .quantity(stock.getQuantity())
                .localDatetime(stock.getLocalDatetime())
                .build();
    }

    public int stockBySku(String sku) {

        int stockAvailable = stockRepository.getBySku(sku).getQuantity();
        log.info("Stock available: {}", stockAvailable);
        return stockAvailable;

    }

    public void updateStockQuantity(String skuReceived, int quantityReceived) {

        var stockActual = stockRepository.getBySku(skuReceived);

        var stockToUpdate = Stock.builder()
                .productId(stockActual.getProductId())
                .sku(skuReceived)
                .name(stockActual.getName())
                .quantity(quantityReceived)
                .build();

        this.stockRepository.save(stockToUpdate);

        log.info("Product updated: {}", stockToUpdate);



    }
}
