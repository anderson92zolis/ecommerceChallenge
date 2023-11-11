package org.ecommerceChallenge.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@FeignClient(name = "stockMicroservice", url = "http://localhost:8082")
public interface StockServiceFeignClient {

    @PostMapping(path= "api/v1/stock/verifyProductId/{productId}")
    String verifyProductIdByQuantity(@PathVariable("productId") int productId,  @RequestParam(value = "orderQuantity") int  orderQuantity);

}
