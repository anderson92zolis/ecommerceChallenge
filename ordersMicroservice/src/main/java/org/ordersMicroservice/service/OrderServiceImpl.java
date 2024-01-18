package org.ordersMicroservice.service;

import org.ecommerceChallenge.clients.ProductServiceFeignClient;
import org.ecommerceChallenge.clients.StockServiceFeignClient;
import org.ordersMicroservice.dto.OrderDto;
import org.ordersMicroservice.dto.OrderRequest;
import org.ordersMicroservice.dto.verify.OrderDetailDocumentVerifiedDto;
import org.ordersMicroservice.dto.verify.OrderVerifiedDto;
import org.ordersMicroservice.entity.OrderDetailDocument;
import org.ordersMicroservice.entity.OrderDocument;
import org.ordersMicroservice.eventsKafka.entity.Message;
import org.ordersMicroservice.eventsKafka.enums.OrderStatus;
import org.ordersMicroservice.exception.EmptyOrderDetailException;
import org.ordersMicroservice.helper.ConverterEntitiesAndDtos;
import org.ordersMicroservice.repository.OrderRepository;
import org.ordersMicroservice.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ConverterEntitiesAndDtos converter;

    @Autowired
    StockServiceFeignClient stockServiceFeignClient;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ProductServiceFeignClient productServiceFeignClient;


    @Override
    public OrderDto saveOrder(OrderRequest orderRequest) {

        OrderRequest orderInProcess = orderRequest;
//        OrderDocument orderToSave = orderDocument;

        if (orderInProcess.getOrderDetail().isEmpty()) {
            throw new EmptyOrderDetailException("The order detail list is empty.");
        }

        // TODO : check CustomerUuid if customer do not exist then throw exception


        List<OrderDetailDocument> toCalculate = new ArrayList<>();

        toCalculate = orderInProcess.getOrderDetail();

        List<OrderDetailDocument> orderDetailwithSubtotal = toCalculate.stream()
                .map(this::calculateItemSubtotal)
                .filter(x -> x != null)
                .toList();

        double subtotal = toCalculate.stream().mapToDouble(s -> s.getItemSubtotal()).sum();

        OrderDocument orderToSave = new OrderDocument();

        orderToSave.setOrderDate(Calendar.getInstance());
        orderToSave.setOrderDetail(orderDetailwithSubtotal);
        orderToSave.setSubtotal(subtotal);
        orderToSave.setTax(subtotal * 21 / 100);
        orderToSave.setCustomerUuid(orderRequest.getCustomerUuid());
        orderRepository.save(orderToSave);
        return converter.entityToDto(orderToSave);
//        return converter.entityToDto(orderRepository.save(orderToSave));
    }

    public OrderDetailDocument calculateItemSubtotal(OrderDetailDocument orderDetailDocument) {

//        String sku = String.valueOf(orderDetailDocument.getProductId());
        String sku = orderDetailDocument.getSku();
//        boolean productExist = productServiceFeignClient.confirmProductBySku(String.valueOf(orderDetailDocument.getProductId()));
        boolean productExist = productServiceFeignClient.confirmProductBySku(sku);
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

        Message messageRequest= null;

        for (OrderDetailDocumentVerifiedDto orderDetailDocumentVerifiedDto: orderDetailwithSubtotal){

            if (orderDetailDocumentVerifiedDto.getVerify().equals("Accepted") ){

                messageRequest= new Message("Order created stock is OKAY ", orderDetailDocumentVerifiedDto.getProductId(), OrderStatus.PLACED);

            } else {

                messageRequest= new Message("Order NOT created stock is NOT okay ", orderDetailDocumentVerifiedDto.getProductId(), OrderStatus.CANCELLED);

            }
            kafkaTemplate.send("placeOrder", String.valueOf(messageRequest));

            // with the function util to deserialize
            // kafkaTemplate.send("placeOrder", JsonUtils.toJson(messageRequest));

        }

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


