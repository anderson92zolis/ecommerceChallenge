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

    @GetMapping(path = "/getSKU/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StockResponse> getSKU( @PathVariable("sku") String sku) {
        StockResponse stockResponse = stockService.getSKU(sku);
        return new ResponseEntity<>(stockResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllStocks")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StockResponse>> getAllStock() {
        List<StockResponse> stockResponseList = stockService.getAllStock();
        return new ResponseEntity<>(stockResponseList, HttpStatus.OK);
    }

    @PostMapping(value = "/addStock")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewStock(@RequestBody StockRequest stockRequest){
        stockService.saveStock(stockRequest);
    }



}
