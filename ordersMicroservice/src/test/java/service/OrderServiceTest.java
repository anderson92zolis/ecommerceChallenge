package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.ordersMicroservice.dto.OrderDto;
import org.ordersMicroservice.dto.OrderRequest;
import org.ordersMicroservice.entity.OrderDetailDocument;
import org.ordersMicroservice.entity.OrderDocument;
import org.ordersMicroservice.exception.EmptyOrderDetailException;
import org.ordersMicroservice.helper.ConverterEntitiesAndDtos;
import org.ordersMicroservice.repository.OrderRepository;
import org.ordersMicroservice.service.OrderServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class OrderServiceTest {
    @Mock
    private OrderRepository orderRepositoryMock;
    @InjectMocks
    OrderServiceImpl orderService;
    @Mock
    private ConverterEntitiesAndDtos converterEntitiesAndDtos;

    List<OrderDetailDocument> orderDetailDocumentList = new ArrayList<>();

    List<OrderDto> orderDtos = new ArrayList<>();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveOrder_ThenGetExpectedId() {

        OrderDetailDocument orderDetailDocument = new OrderDetailDocument(1, 2, 2, 1, 2.0);

        orderDetailDocumentList.add(orderDetailDocument);

        OrderDocument orderDocument = OrderDocument.builder()
                .id(1)
                .orderDate(Calendar.getInstance())
                .orderDetail(orderDetailDocumentList)
                .subtotal(2)
                .tax(0.4)
                .build();

        when(orderRepositoryMock.save(orderDocument)).thenReturn(orderDocument);
        int idExpected = 1;
        assertEquals(idExpected, orderDocument.getId());
        assertThat(orderDocument).isNotNull();
    }

    @Test
    void throwsEmptyOrderDetailException(){

/*        OrderDocument orderDocument = OrderDocument.builder()
                .id(1)
                .orderDate(Calendar.getInstance())
                .orderDetail(orderDetailDocumentList)
                .subtotal(2)
                .tax(0.4)
                .build();
*/
        OrderRequest orderRequest = OrderRequest.builder()
                .customerUuid(null)
                .orderDetail(orderDetailDocumentList)
                .build();

        OrderServiceImpl orderService1 = new OrderServiceImpl();
        assertThrows(EmptyOrderDetailException.class, () -> orderService1.saveOrder(orderRequest));
    }

    @Test
    void listAll_ThenCheckSize() {

        OrderDetailDocument orderDetailDocument = new OrderDetailDocument(1, 2, 2, 1, 2.0);

        orderDetailDocumentList.add(orderDetailDocument);

        OrderDto orderDto1 = OrderDto.builder()
                .id(1)
                .orderDate(Calendar.getInstance())
                .orderDetail(orderDetailDocumentList)
                .subtotal(2)
                .tax(0.4)
                .build();

        OrderDto orderDto2 = OrderDto.builder()
                .id(2)
                .orderDate(Calendar.getInstance())
                .orderDetail(orderDetailDocumentList)
                .subtotal(2)
                .tax(0.4)
                .build();

        List<OrderDocument> orderDocumentList = new ArrayList<>();
        orderDtos.add(orderDto2);
        orderDtos.add(orderDto1);

        when(orderService.findAll()).thenReturn(orderDtos);
        int dtosCount = 2;
        assertEquals(dtosCount, orderDtos.size());
    }
}
