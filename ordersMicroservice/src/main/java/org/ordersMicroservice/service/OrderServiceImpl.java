package org.ordersMicroservice.service;

import org.ordersMicroservice.dto.OrderDto;
import org.ordersMicroservice.entity.OrderDocument;
import org.ordersMicroservice.helper.ConverterEntitiesAndDtos;
import org.ordersMicroservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ConverterEntitiesAndDtos converter;

    @Override
    public OrderDto saveOrder(OrderDocument orderDocument) {

        OrderDocument orderSaved = orderRepository.save(orderDocument);


        return converter.entityToDto(orderSaved);
    }
}
