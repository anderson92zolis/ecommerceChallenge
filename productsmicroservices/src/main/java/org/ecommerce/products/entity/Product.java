package org.ecommerce.products.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name="product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name= "SKU")
    private String sku;

    @Column(name="ProductName", length=100, unique=true,  nullable= false)
    private String name;

    @Column(name = "Description")
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "Price")
    private float price;

    private String manufacturer;

    private String supplier;

    @CreationTimestamp
    private LocalDateTime localDatetime;


    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", manufacturer='" + manufacturer + '\'' +
                ", supplier='" + supplier + '\'' +
                ", localDatetime=" + localDatetime +
                '}';
    }

}