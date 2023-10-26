package org.ordersMicroservice.service;

import lombok.experimental.Helper;
import org.ordersMicroservice.entity.OrderDocument;
import org.ordersMicroservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    Helper helper;

    @Override
    public OrderDocument saveOrder(OrderDocument orderDocument) {

        OrderDocument orderSaved = orderRepository.save(orderDocument);


        return null;
    }
}
