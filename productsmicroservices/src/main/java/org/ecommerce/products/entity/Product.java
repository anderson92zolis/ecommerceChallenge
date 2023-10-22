package org.ecommerce.products.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

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

    /*
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
    */
}