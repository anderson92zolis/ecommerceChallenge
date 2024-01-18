package org.ordersMicroservice.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ordersMicroservice.dto.OrderDto;
import org.ordersMicroservice.entity.OrderDetailDocument;
import org.ordersMicroservice.entity.OrderDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
class OrderRepositoryTest {

    @Container
    static MySQLContainer mySQLContainer = new MySQLContainer ("mysql");

    @DynamicPropertySource
    static void mySQLProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", () ->mySQLContainer.getJdbcUrl());
        registry.add("spring.datasource.driverClassName", () -> mySQLContainer.getDriverClassName());
        registry.add("spring.datasource.username", () -> mySQLContainer.getUsername());
        registry.add("spring.datasource.password", () -> mySQLContainer.getPassword());;
    }

    @Autowired
    private OrderRepository orderRepository;

    OrderDocument orderDocument1;
    List<OrderDetailDocument> orderDetailDocumentList = new ArrayList<>();
    List<OrderDto> orderDtos = new ArrayList<>();

    OrderDto orderDto;

    @BeforeEach
    void setUp(){

        OrderDetailDocument orderDetailDocument = new OrderDetailDocument(null, 2,null, 2, 1, 2.0);

        orderDetailDocumentList.add(orderDetailDocument);

        orderDocument1 = OrderDocument.builder()
                .orderDate(Calendar.getInstance())
                .orderDetail(orderDetailDocumentList)
                .subtotal(2)
                .tax(0.4)
                .build();


        orderRepository.save(orderDocument1);
    }

    @Test
    void findAllTest(){

        int expected = 1;
        List<OrderDocument> existing = orderRepository.findAll();
        assertEquals(expected, existing.size());
    }
}