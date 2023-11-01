package org.ordersMicroservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "order_detail")
public class OrderDetailDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int productId;
    private double productPrice;
    private int productQuantity;
    private double itemSubtotal;
}
