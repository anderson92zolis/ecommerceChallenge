package org.customerMicroservices.documents;

import org.customerMicroservices.dto.AddressDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDocument_Test {

    @Test
    public void testCustomerDocumentCreation() {
        //region VARIABLES
        UUID uuid;
        AddressDTO addressDTO;
        List<Integer> ordersList;
        CustomerDocument customer1;
        CustomerDocument customer2;
        CustomerDocument customer3;

        //endregion VARIABLES


        //region TEST INITIALIZATION
        uuid = UUID.randomUUID();
        addressDTO = new AddressDTO("Street", "City", 12345, "Country");
        customer1 = new CustomerDocument();
        ordersList = Arrays.asList(1, 2, 3);
        customer2 = CustomerDocument.builder()
                .uuid(UUID.randomUUID())
                .pass("user1Password")
                .name("user1")
                .dni("12345678Z")
                .address(addressDTO)
                .ordersList(ordersList)
                .build();
        customer3 = new CustomerDocument();
        customer3.setUuid(uuid);
        customer3.setPass("customer3Pass");
        customer3.setName("customer3");
        customer3.setDni("12345678Z");
        customer3.setAddress(addressDTO);
        customer3.setOrdersList(ordersList);

        //endregion TEST INITIALIZATION


        //region TEST
        assertNotNull(customer1);
        assertNotNull(customer2);
        assertNotNull(customer2.getUuid());
        assertEquals("user1Password", customer2.getPass());
        assertEquals("user1", customer2.getName());
        assertEquals("12345678Z", customer2.getDni());
        assertNotNull(customer2.getAddress());
        assertNotNull(customer2.getOrdersList());
        assertEquals(3, customer2.getOrdersList().size());

        //endregion TEST

    }


}