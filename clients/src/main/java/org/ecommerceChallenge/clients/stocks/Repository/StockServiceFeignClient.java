package org.ecommerceChallenge.clients.stocks.Repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("Stocks")
public interface StockServiceFeignClient {

    @GetMapping(path= "api/v1/stock/verifyProductId/{productId}")
    String verifyProductIdByQuantity(@PathVariable("productId") int productId, int orderQuantity);

}
