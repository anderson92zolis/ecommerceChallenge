package org.ecommerceChallenge.stockMicro.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="stock") //name of the table in our database sometime it is not necessary
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Data
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "productId")
    private int productId;
    @Column(name= "SKU")
    private String sku;
    @Column(name="productName", length=100, unique=true,  nullable= false)
    private String name;
    @Column(name = "Quantity")
    private int quantity;
    @CreationTimestamp
    private LocalDateTime localDatetime;

}