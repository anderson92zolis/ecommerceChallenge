package org.ecommerceChallenge.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@FeignClient(name = "stocks", url = "http://localhost:8080")
public interface StockServiceFeignClient {

    @PostMapping(path= "api/v1/stock/verifyProductId/{productId}")
    String verifyProductIdByQuantity(@PathVariable("productId") int productId,  @RequestParam(value = "orderQuantity") int  orderQuantity);

    @GetMapping(path = "api/v1/stock/countStock/{sku}")
    int countStockBySku (@PathVariable String sku);

    @PostMapping(path = "api/v1/stock/reduceStock/{skuReceived}/{quantityReceived}")
    void reduceStock (@PathVariable String skuReceived, @PathVariable int quantityReceived);
    }
