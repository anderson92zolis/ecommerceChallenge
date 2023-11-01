package org.ordersMicroservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "order_document")
public class OrderDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Temporal(TemporalType.DATE)
    private Calendar orderDate;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<OrderDetailDocument> orderDetail;
    private double subtotal;
    private double tax;
}
