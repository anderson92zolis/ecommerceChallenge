package org.ordersMicroservice.service;

import org.ordersMicroservice.dto.OrderDto;
import org.ordersMicroservice.dto.OrderRequest;
import org.ordersMicroservice.dto.verify.OrderVerifiedDto;
import org.ordersMicroservice.entity.OrderDetailDocument;
import org.ordersMicroservice.entity.OrderDocument;
import java.util.List;

public interface OrderService {

    OrderDto saveOrder (OrderRequest orderRequest);
    OrderDetailDocument calculateItemSubtotal(OrderDetailDocument orderDetailDocument);
    List<OrderDto> findAll();

    OrderVerifiedDto verifyOrderStocks(OrderDocument orderDocument);

//    OrderVerifiedDto verifyOrderStocks(OrderDocument orderDocument);
}
