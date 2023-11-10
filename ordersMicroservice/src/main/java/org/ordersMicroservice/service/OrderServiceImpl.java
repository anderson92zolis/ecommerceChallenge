package org.ordersMicroservice.service;

import org.ecommerceChallenge.clients.stocks.Repository.StockServiceFeignClient;
import org.ordersMicroservice.dto.OrderDto;
import org.ordersMicroservice.dto.verify.OrderDtoVerify;
import org.ordersMicroservice.entity.OrderDetailDocument;
import org.ordersMicroservice.entity.OrderDocument;
import org.ordersMicroservice.exception.EmptyOrderDetailException;
import org.ordersMicroservice.helper.ConverterEntitiesAndDtos;
import org.ordersMicroservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ConverterEntitiesAndDtos converter;

    @Autowired
    StockServiceFeignClient stockServiceFeignClient;

    @Override
    public OrderDto saveOrder(OrderDocument orderDocument) {

        OrderDocument orderToSave = orderDocument;

        if(orderToSave.getOrderDetail().isEmpty()){
            throw new EmptyOrderDetailException("The order detail list is empty.");
        }

        List<OrderDetailDocument> toCalculate = new ArrayList<>();

        toCalculate = orderToSave.getOrderDetail();

        List<OrderDetailDocument> orderDetailwithSubtotal = toCalculate.stream()
                .map(this::calculateItemSubtotal)
                .toList();

        double subtotal = toCalculate.stream().mapToDouble(s -> s.getItemSubtotal()).sum();

        orderToSave.setOrderDate(Calendar.getInstance());
        orderToSave.setOrderDetail(orderDetailwithSubtotal);
        orderToSave.setSubtotal(subtotal);
        orderToSave.setTax(subtotal * 21 / 100);

        return converter.entityToDto(orderRepository.save(orderToSave));
    }

    public OrderDetailDocument calculateItemSubtotal(OrderDetailDocument orderDetailDocument){

        double price = orderDetailDocument.getProductPrice();
        int quantity = orderDetailDocument.getProductQuantity();
        orderDetailDocument.setItemSubtotal(price * quantity);
        return orderDetailDocument;
    }

    @Override
    public List<OrderDto> findAll() {

        List<OrderDocument> orderDocumentList = orderRepository.findAll();

        List<OrderDto> OrdersToReturn = orderDocumentList.stream()
                .map(s -> converter.entityToDto(s))
                .toList();

        return OrdersToReturn;
    }


    // OpenFeign

    public OrderDtoVerify verifyOrderStocks(OrderDocument orderDocument) {

        if(orderDocument.getOrderDetail().isEmpty()){
            throw new EmptyOrderDetailException("The order detail list is empty.");
        }

        List<OrderDetailDocument> toCalculate = orderDocument.getOrderDetail();

        List<OrderDetailDocument> orderDetailwithSubtotal = toCalculate.stream()
                .map(this::calculateItemSubtotal)
                .toList();

        double subtotal = toCalculate.stream().mapToDouble(s -> s.getItemSubtotal()).sum();

        orderDocument.setOrderDate(Calendar.getInstance());
        orderDocument.setOrderDetail(orderDetailwithSubtotal);
        orderDocument.setSubtotal(subtotal);
        orderDocument.setTax(subtotal * 21 / 100);

        return converter.entityToDto(orderRepository.save(orderDocument));
    }







}
