package org.ordersMicroservice.service;

import org.ordersMicroservice.entity.OrderDocument;

public interface OrderService {

    OrderDocument saveOrder (OrderDocument orderDocument);
}
