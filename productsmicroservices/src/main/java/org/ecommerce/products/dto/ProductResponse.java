package org.ecommerce.products.dto;

import lombok.Builder;
import lombok.Getter;
import org.ecommerce.products.entity.Category;

@Builder
@Getter
public class ProductResponse {
    private String name;
    private String description;
    private Category category;
    private float price;
}
