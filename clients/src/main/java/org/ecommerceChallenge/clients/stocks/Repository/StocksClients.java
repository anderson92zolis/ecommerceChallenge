package org.ecommerceChallenge.clients.stocks.Repository;

import lombok.Getter;
import org.ecommerceChallenge.clients.stocks.dto.StockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("stocks")
public interface StocksClients {

    @GetMapping(path= "api/v1/stock/getSKU/{sku}")
    StockResponse stockResponse(
            @PathVariable("sku") String sku);

}
