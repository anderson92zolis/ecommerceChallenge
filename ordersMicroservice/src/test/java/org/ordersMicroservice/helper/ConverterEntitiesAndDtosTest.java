package org.ordersMicroservice.helper;

import org.junit.jupiter.api.Test;
import org.ordersMicroservice.dto.OrderDto;
import org.ordersMicroservice.entity.OrderDetailDocument;
import org.ordersMicroservice.entity.OrderDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConverterEntitiesAndDtosTest {

    @Test
    void entityToDtoTest() {

    OrderDetailDocument orderDetailDocument = new OrderDetailDocument(1, 2,"000001", 2, 1, 2.0);
    List<OrderDetailDocument> orderDetailDocumentList = new ArrayList<>();
    orderDetailDocumentList.add(orderDetailDocument);

    OrderDocument orderDocument = OrderDocument.builder()
                .id(1)
                .customerUuid(null)
                .orderDate(Calendar.getInstance())
                .orderDetail(orderDetailDocumentList)
                .subtotal(2)
                .tax(0.4)
                .build();

    ConverterEntitiesAndDtos converterEntitiesAndDtos = new ConverterEntitiesAndDtos();
    OrderDto orderDto = converterEntitiesAndDtos.entityToDto(orderDocument);

    assertEquals(orderDocument.getId(),orderDto.getId());
    }
}