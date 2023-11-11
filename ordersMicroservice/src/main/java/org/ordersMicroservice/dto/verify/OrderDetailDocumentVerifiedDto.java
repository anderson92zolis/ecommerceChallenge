package org.ordersMicroservice.dto.verify;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class OrderDetailDocumentVerifiedDto {

    @Schema(description = "product Id")
    private Integer id;

    @Schema(description = "This is the id of the product sold")
    private int productId;

    @Schema(description = "This is the price of the product sold")
    private double productPrice;

    @Schema(description = "This is the quantity sold of this product")
    private int productQuantity;

    @Schema(description = "This is price x quantity")
    private double itemSubtotal;

    @Schema(description = "This is the acepted for product if the order es less that quantity in stock")
    private String verify;
}
