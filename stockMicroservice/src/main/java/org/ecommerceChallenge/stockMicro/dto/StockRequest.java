package org.ecommerceChallenge.stockMicro.dto;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter // adding for thymeleaf
public class StockRequest {

    private String sku;
    private String name;
    private int quantity;

}