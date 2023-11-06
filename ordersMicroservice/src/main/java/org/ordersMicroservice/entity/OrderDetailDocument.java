package org.ordersMicroservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "order_detail")
public class OrderDetailDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Schema(description = "This is the id of the product sold")
    private int productId;
    @Schema(description = "This is the price of the product sold")
    private double productPrice;
    @Schema(description = "This is the quantity sold of this product")
    private int productQuantity;
    @Schema(description = "This is price x quantity")
    private double itemSubtotal;
}
