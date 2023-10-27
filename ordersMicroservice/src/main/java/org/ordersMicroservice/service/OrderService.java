package org.ordersMicroservice.service;

import org.ordersMicroservice.dto.OrderDto;
import org.ordersMicroservice.entity.OrderDocument;

public interface OrderService {

    OrderDto saveOrder (OrderDocument orderDocument);
}
