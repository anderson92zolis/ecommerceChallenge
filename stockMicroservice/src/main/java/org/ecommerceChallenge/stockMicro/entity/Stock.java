package org.ecommerceChallenge.stockMicro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="stock") //name of the table in our database sometime it is not necessary
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Stock {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stockId;

    @Column(name= "SKU")
    private String sku;

    @Column(name="productName", length=100, unique=true,  nullable= false)
    private String name;

    @Column(name = "Quantity")
    private int quantity;

    @CreationTimestamp
    private LocalDateTime localDatetime;

}