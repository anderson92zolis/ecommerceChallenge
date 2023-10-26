package org.ordersMicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ordersMicroservice.entity.OrderDetailDocument;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private int id;
    @Temporal(TemporalType.DATE)
    private Calendar orderDate;
    private List<OrderDetailDocument> orderDetail;
    private double subtotal;
    private double tax;

}
