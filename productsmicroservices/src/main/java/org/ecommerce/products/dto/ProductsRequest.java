package org.ecommerce.products.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.ecommerce.products.entity.Category;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter // adding for thymeleaf
public class ProductsRequest {
    private int sku;
    private String name;
    private String description;
    private Category category;
    private float price;
    private String manufacturer;
    private String supplier;
}