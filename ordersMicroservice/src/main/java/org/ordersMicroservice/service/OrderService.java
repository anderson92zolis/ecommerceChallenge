package org.ordersMicroservice.service;

import org.ordersMicroservice.dto.OrderDto;
import org.ordersMicroservice.entity.OrderDocument;
import java.util.List;

public interface OrderService {

    OrderDto saveOrder (OrderDocument orderDocument);

    List<OrderDto> findAll();
}
