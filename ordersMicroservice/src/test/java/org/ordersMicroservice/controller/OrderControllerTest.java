package org.ordersMicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.ordersMicroservice.dto.OrderDto;
import org.ordersMicroservice.dto.OrderRequest;
import org.ordersMicroservice.entity.OrderDetailDocument;
import org.ordersMicroservice.entity.OrderDocument;
import org.ordersMicroservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderService orderServiceMock;
    @InjectMocks
    private OrderController orderController;
    @Autowired
    private ObjectMapper objectMapper;
    List<OrderDetailDocument> orderDetailDocumentList = new ArrayList<>();
    List<OrderDto> orderDtos = new ArrayList<>();
    OrderDocument orderDocument;
    OrderDto orderDto;

    @BeforeEach
    void setUp(){

        MockitoAnnotations.openMocks(this);

        OrderDetailDocument orderDetailDocument = new OrderDetailDocument(1, 2, 2, 1, 2.0);

        orderDetailDocumentList.add(orderDetailDocument);

        orderDocument = OrderDocument.builder()
                .id(1)
                .orderDate(null)
                .orderDetail(orderDetailDocumentList)
                .subtotal(2)
                .tax(0.4)
                .build();

        orderDto = OrderDto.builder()
                .id(1)
                .orderDate(null)
                .orderDetail(orderDetailDocumentList)
                .subtotal(2)
                .tax(0.4)
                .build();
       when(orderServiceMock.saveOrder(Mockito.any(OrderRequest.class))).thenReturn(orderDto);

       orderDtos.add(orderDto);
       when(orderServiceMock.findAll()).thenReturn(orderDtos);
    }

    @Test
    void saveOrderControllerTest()throws Exception{

        this.mockMvc
                .perform(post("/api/v1/orders/add").content(asJsonString(orderDocument))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andDo(print())
                .andExpect(jsonPath("$.id").value(orderDocument.getId()))
                .andExpect(jsonPath("$.id").value(notNullValue()))
                .andReturn();
    }

    @Test
    void listAllControllerTest () throws Exception{

        mockMvc.perform(get("/api/v1/orders/getAll").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").value("1"))
                .andExpect(jsonPath("$", hasSize(1)))//;
                .andExpect(content().json(asJsonString(orderDtos)));
    }


    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
