package org.ecommerceChallenge.stockMicro.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerceChallenge.stockMicro.dto.StockRequest;
import org.ecommerceChallenge.stockMicro.dto.StockResponse;
import org.ecommerceChallenge.stockMicro.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "api/v1/stock")
public class StockController {

    private final StockService stockService;

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(value = "/test")
    public String test() {
        log.info("** Saludos desde el logger **");


        return "Hello from Stock DB!!!";

    }

    @GetMapping(value = "/getAllStocks")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StockResponse>> getAllStock() {
        List<StockResponse> stockResponseList = stockService.getAllStock();
        return new ResponseEntity<>(stockResponseList, HttpStatus.OK);
    }

    @PostMapping(value = "/addStock")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewStock(@RequestBody StockRequest stockRequest) {
        stockService.saveStock(stockRequest);

    }



    @PostMapping(path = "/verifyProductId/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public String verifyProductIdByQuantity(@PathVariable("productId") int productId, @RequestParam(value = "orderQuantity") int orderQuantity) {
        String verifyQuantity = stockService.verifyProductIdByQuantity(productId,orderQuantity);
        return verifyQuantity;
    }

    /*
     * Feign methods
     */

    @GetMapping(value = "/countStock/{sku}")
    public int countStockBySku (@PathVariable String sku) {
        return stockService.stockBySku(sku);
    }

    @PostMapping(value = "/reduceStock/{skuReceived}/{quantityReceived}")
    public void reduceStock (@PathVariable String skuReceived, @PathVariable int quantityReceived){

        stockService.updateStockQuantity(skuReceived, quantityReceived);

    }


}
