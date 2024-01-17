package org.ordersMicroservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ordersMicroservice.entity.OrderDetailDocument;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

//  private int id;
    private UUID customerUuid;
//    private Calendar orderDate;
    private List<OrderDetailDocument> orderDetail;
//    private double subtotal;
//    private double tax;



}
