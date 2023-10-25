package entity;

import org.junit.jupiter.api.Test;
import org.ordersMicroservice.entity.OrderDetailDocument;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderDetailTest {

    private int idTest = 1;
    private int productIdTest =15;
    private double productPriceTest = 8.0;
    private int productQuantityTest = 2;
    private double itemSubtotalTest = productPriceTest * productQuantityTest;

    OrderDetailDocument detailDocument = OrderDetailDocument.builder()
            .id(idTest)
            .productId(productIdTest)
            .productPrice(productPriceTest)
            .productQuantity(productQuantityTest)
            .itemSubtotal(itemSubtotalTest)
            .build();

    @Test
    void getId(){
        assertEquals(1, detailDocument.getId());
    }
    @Test
    void getProductId(){
        assertEquals(15, detailDocument.getProductId());
    }
    @Test
    void getProductPrice(){
        assertEquals(8.0, detailDocument.getProductPrice() );
    }
    @Test
    void getProductQuantity(){
        assertEquals(2, detailDocument.getProductQuantity());
    }
    @Test
    void getItemSubtotal(){
        assertEquals(16.0, detailDocument.getItemSubtotal());
    }
}
