package org.ecommerce.products.entity;

import jdk.jfr.Name;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(unique = true)
    private String name;

    private String description;

    private Category category;

    private Double price;

    private String manufacturer;

    private String supplier;

    private LocalDate creationDate;

}