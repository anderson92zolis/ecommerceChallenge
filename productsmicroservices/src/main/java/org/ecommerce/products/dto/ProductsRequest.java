package org.ecommerce.products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ecommerce.products.entity.Category;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ProductsRequest {
    private int sku;
    private String name;
    private String description;
    private Category category;
    private float price;
    private String manufacturer;
    private String supplier;
}