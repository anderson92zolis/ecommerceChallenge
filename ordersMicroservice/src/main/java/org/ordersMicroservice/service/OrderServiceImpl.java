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
public class OrderServiceImpl implements OrderService {

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

        if (orderToSave.getOrderDetail().isEmpty()) {
            throw new EmptyOrderDetailException("The order detail list is empty.");
        }

        List<OrderDetailDocument> toCalculate = new ArrayList<>();

        toCalculate = orderToSave.getOrderDetail();

        List<OrderDetailDocument> orderDetailwithSubtotal = toCalculate.stream()
                .map(this::calculateItemSubtotal)
                .filter(x -> x != null)
                .toList();

        double subtotal = toCalculate.stream().mapToDouble(s -> s.getItemSubtotal()).sum();

        orderToSave.setOrderDate(Calendar.getInstance());
        orderToSave.setOrderDetail(orderDetailwithSubtotal);
        orderToSave.setSubtotal(subtotal);
        orderToSave.setTax(subtotal * 21 / 100);

        return converter.entityToDto(orderRepository.save(orderToSave));
    }

    public OrderDetailDocument calculateItemSubtotal(OrderDetailDocument orderDetailDocument) {

        String sku = String.valueOf(orderDetailDocument.getProductId());

        boolean productExist = productServiceFeignClient.confirmProductBySku(String.valueOf(orderDetailDocument.getProductId()));
        if (!productExist) {
            return null;
        }
        // TODO replace String.valueOf by sku

        double price = productServiceFeignClient.getProductById(sku).getPrice();
        int quantity = orderDetailDocument.getProductQuantity();
        int stock = stockServiceFeignClient.countStockBySku(sku);

        if (stock == 0) {
            return null;
        }
        if (stock < quantity) {
            quantity = stock;
        }
        int newStock = stock - quantity;
        orderDetailDocument.setProductQuantity(quantity);
        orderDetailDocument.setProductPrice(price);
        orderDetailDocument.setItemSubtotal(price * quantity);
        stockServiceFeignClient.reduceStock(sku, newStock);
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
}


