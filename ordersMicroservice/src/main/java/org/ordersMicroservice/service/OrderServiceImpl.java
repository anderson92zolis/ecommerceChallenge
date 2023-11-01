package org.ordersMicroservice.service;

import org.ordersMicroservice.dto.OrderDto;
import org.ordersMicroservice.entity.OrderDetailDocument;
import org.ordersMicroservice.entity.OrderDocument;
import org.ordersMicroservice.helper.ConverterEntitiesAndDtos;
import org.ordersMicroservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ConverterEntitiesAndDtos converter;

    @Override
    public OrderDto saveOrder(OrderDocument orderDocument) {

        List<OrderDetailDocument> toCalculate = orderDocument.getOrderDetail();

        double subtotal = toCalculate.stream().mapToDouble(s -> s.getItemSubtotal()).sum();

        OrderDocument orderToSave = new OrderDocument();
        orderToSave.setOrderDate(Calendar.getInstance());
        orderToSave.setOrderDetail(orderDocument.getOrderDetail());
        orderToSave.setSubtotal(subtotal);
        orderToSave.setTax(subtotal * 21 / 100);
        OrderDocument orderSaved = orderRepository.save(orderToSave);

        return converter.entityToDto(orderSaved);
    }

    @Override
    public List<OrderDto> findAll() {

        List<OrderDocument> orderDocumentList = orderRepository.findAll();

        List<OrderDto> OrdersToReturn = orderDocumentList.stream()
                .map(s -> converter.entityToDto(s))
                .toList();

        return OrdersToReturn;
    }
}
