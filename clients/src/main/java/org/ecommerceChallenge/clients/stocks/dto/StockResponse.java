package org.ecommerceChallenge.clients.stocks.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class StockResponse {
    private int stockId;
    private String sku;
    private String name;
    private int quantity;
    private LocalDateTime localDatetime;
}
