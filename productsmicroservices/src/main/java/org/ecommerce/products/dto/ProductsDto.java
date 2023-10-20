package org.ecommerce.products.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ecommerce.products.entity.Category;


import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class ProductsDto {
    private String name;
    private String description;
    private Category category;
    private float price;
    private String manufacturer;
    private String supplier;
}