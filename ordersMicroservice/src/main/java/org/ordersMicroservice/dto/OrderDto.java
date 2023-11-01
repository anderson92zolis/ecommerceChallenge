package org.ordersMicroservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ordersMicroservice.entity.OrderDetailDocument;

import java.util.Calendar;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    @Schema(description = "This is the id of the order")
    private int id;
    @Schema(description = "This is the date when the order was created")
    private Calendar orderDate;
    private List<OrderDetailDocument> orderDetail;
    @Schema(description = "The subtotal is calculated by the program")
    private double subtotal;
    @Schema(description = "This is the 21% tax and is calculated by the program")
    private double tax;

}
