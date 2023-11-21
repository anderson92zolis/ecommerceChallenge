package dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductResponse {
    private String sku; // TODO: for debuging porpouse
    private String name;
    private String description;
    private Category category;
    private float price;
}
