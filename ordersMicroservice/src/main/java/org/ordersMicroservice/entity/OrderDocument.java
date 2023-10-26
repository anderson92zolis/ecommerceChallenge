package org.ordersMicroservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table (name = "order")
public class OrderDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Temporal(TemporalType.DATE)
    private Calendar orderDate;
    private List<OrderDetailDocument> orderDetail;
    private double subtotal;
    private double tax;
}
