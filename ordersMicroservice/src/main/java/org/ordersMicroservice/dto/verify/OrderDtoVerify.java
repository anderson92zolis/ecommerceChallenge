package org.ordersMicroservice.dto.verify;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ordersMicroservice.dto.verify.OrderDetailDocumentVerified;

import java.util.Calendar;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDtoVerify {

    @Schema(description = "This is the id of the order")
    private int id;
    @Schema(description = "This is the date when the order was created")
    private Calendar orderDate;
    @Schema(description = "This List contains the data provided by the OrderDetailDocument")
    private List<OrderDetailDocumentVerified> orderDetailDocumentVerified;
    @Schema(description = "The subtotal is calculated by the program")
    private double subtotal;
    @Schema(description = "This is the 21% tax and is calculated by the program")
    private double tax;


}
