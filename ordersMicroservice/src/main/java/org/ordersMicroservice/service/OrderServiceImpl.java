package org.ordersMicroservice.service;

import org.ecommerceChallenge.clients.ProductServiceFeignClient;
import org.ecommerceChallenge.clients.StockServiceFeignClient;
import org.ordersMicroservice.dto.OrderDto;
import org.ordersMicroservice.dto.verify.OrderDetailDocumentVerifiedDto;
import org.ordersMicroservice.dto.verify.OrderVerifiedDto;
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
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ConverterEntitiesAndDtos converter;

    @Autowired
    StockServiceFeignClient stockServiceFeignClient;

    @Autowired
    ProductServiceFeignClient productServiceFeignClient;

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

        double price =  productServiceFeignClient.getProductById(String.valueOf(orderDetailDocument.getProductId())).getPrice();
        int quantity = orderDetailDocument.getProductQuantity();
        orderDetailDocument.setProductPrice(price);
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

    public OrderVerifiedDto verifyOrderStocks(OrderDocument orderDocument) {

        if(orderDocument.getOrderDetail().isEmpty()){
            throw new EmptyOrderDetailException("The order detail list is empty.");
        }

        List<OrderDetailDocument> toCalculate = orderDocument.getOrderDetail();

        List<OrderDetailDocumentVerifiedDto> orderDetailwithSubtotal = toCalculate.stream()
                .map(this::calculateItemSubtotalVerify)
                .toList();

        double subtotal = toCalculate.stream().mapToDouble(s -> s.getItemSubtotal()).sum();

        OrderVerifiedDto orderVerifiedDto= new OrderVerifiedDto();

        orderVerifiedDto.setOrderDate(Calendar.getInstance());
        orderVerifiedDto.setOrderDetailDocumentVerified(orderDetailwithSubtotal);
        orderVerifiedDto.setSubtotal(subtotal);
        orderVerifiedDto.setTax(subtotal * 21 / 100);

        return orderVerifiedDto;
    }

    public OrderDetailDocumentVerifiedDto calculateItemSubtotalVerify(OrderDetailDocument orderDetailDocument){

        double price = orderDetailDocument.getProductPrice();
        int quantity = orderDetailDocument.getProductQuantity();
        orderDetailDocument.setItemSubtotal(price * quantity);
        OrderDetailDocumentVerifiedDto orderDetailDocumentVerified = converter.orderDetailsDocumentsToOrderDetailsDocumentVerifiedDto(orderDetailDocument);

        orderDetailDocumentVerified.setVerify(stockServiceFeignClient.verifyProductIdByQuantity(orderDetailDocument.getProductId(), orderDetailDocument.getProductQuantity()));

        System.out.println(orderDetailDocumentVerified);

        return orderDetailDocumentVerified;
    }
}
