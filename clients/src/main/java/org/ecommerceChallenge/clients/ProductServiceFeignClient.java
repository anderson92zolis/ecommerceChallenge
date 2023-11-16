package org.ecommerceChallenge.clients;

import dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products", url = "http://localhost:55876")
public interface ProductServiceFeignClient {

    @GetMapping(path = "api/v1/products/getProduct/{sku}")
    public ProductResponse getProductById (@PathVariable String sku);

}
