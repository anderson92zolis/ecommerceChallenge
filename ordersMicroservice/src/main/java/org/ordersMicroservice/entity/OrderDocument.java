package org.ordersMicroservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "order_document")
public class OrderDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "This is the id of the order")
    private int id;
    @Schema(description = "This is the UUID to store the customer into the order")
    private UUID customerUuid;
    @Temporal(TemporalType.DATE)
    @Schema(description = "This is the date when the order was created")
    private Calendar orderDate;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @Schema(description = "This List contains the data provided by the OrderDetailDocument")
    private List<OrderDetailDocument> orderDetail;

    @Schema(description = "The subtotal is calculated by the program")
    private double subtotal;
    @Schema(description = "This is the 21% tax and is calculated by the program")
    private double tax;
}
