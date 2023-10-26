package org.ordersMicroservice.service;

import org.ordersMicroservice.entity.OrderDetailDocument;
import org.ordersMicroservice.entity.OrderDocument;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetailDocument> saveOrderDetail (OrderDocument orderDocument);
}
