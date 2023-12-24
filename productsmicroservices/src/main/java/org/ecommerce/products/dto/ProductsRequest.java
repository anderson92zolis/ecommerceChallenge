package org.ecommerce.products.dto;


import lombok.*;
import org.ecommerce.products.entity.Category;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter // adding for thymeleaf
public class ProductsRequest {
    private String sku;
    private String name;
    private String description;
    private Category category;
    private float price;
    private String manufacturer;
    private String supplier;
}