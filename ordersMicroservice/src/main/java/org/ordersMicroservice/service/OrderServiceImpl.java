package org.ordersMicroservice.service;

import lombok.experimental.Helper;
import org.ordersMicroservice.dto.OrderDto;
import org.ordersMicroservice.entity.OrderDocument;
import org.ordersMicroservice.helper.Converter;
import org.ordersMicroservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    Converter converter;

    @Autowired
    Helper helper;

    @Override
    public OrderDto saveOrder(OrderDocument orderDocument) {

        OrderDocument orderSaved = orderRepository.save(orderDocument);


        return converter.entityToDto(orderSaved);
    }
}
