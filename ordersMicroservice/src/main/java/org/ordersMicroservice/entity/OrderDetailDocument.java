package org.ordersMicroservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "order_detail")
public class OrderDetailDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int productId;
    private double productPrice;
    private int productQuantity;
    private double itemSubtotal;
}
