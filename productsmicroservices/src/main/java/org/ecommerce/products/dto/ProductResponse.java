package org.ecommerce.products.dto;

import lombok.Builder;
import lombok.Getter;
import org.ecommerce.products.entity.Category;

import java.util.Objects;

@Builder
@Getter
public class ProductResponse {
    private String sku; // TODO: for debuging porpouse
    private String name;
    private String description;
    private Category category;
    private float price;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ProductResponse that = (ProductResponse) obj;

        return Objects.equals(sku, that.sku) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(category, that.category) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, name, description, category, price);
    }
}
