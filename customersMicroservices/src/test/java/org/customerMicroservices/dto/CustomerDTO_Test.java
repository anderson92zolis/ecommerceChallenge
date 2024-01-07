package org.customerMicroservices.dto;

import org.customerMicroservices.helpers.APPMthd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDTO_Test {
    //region VARIABLES
    private UUID uuid1;
    private ArrayList<Integer> ordersList1;
    private AddressDTO address1;
    private CustomerDTO customer1;

    //endregion VARIABLES


    //region CONSTRUCTOR
    @BeforeEach
    void setup() {
        //region ACTIONS
        uuid1 = UUID.randomUUID();
        ordersList1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 6, 7, 8));
        address1 = AddressDTO.builder()
                .street("Cartagena, 2")
                .city("Barcelona")
                .postalCode(8023)
                .country("España").build();
        customer1 = CustomerDTO.builder()
                .uuid(uuid1)
                .name("Fernadno")
                .dni("78124592D")
                .address(address1)
                .ordersList(ordersList1).build();

        //endregion ACTIONS

    }


    //endregion CONSTRUCTOR


    //region TESTS
    @Test
    void getId() {
        //region TEST
        assertEquals(uuid1, customer1.getUuid());

        //endregion TEST

    }

    @Test
    void getName() {
        //region TEST
        assertEquals("Fernadno", customer1.getName());

        //endregion TEST

    }

    @Test
    void getDni() {
        //region TEST
        assertEquals("78124592D", customer1.getDni());

        //endregion TEST

    }

    @Test
    void getAddress() {
        //region TEST
        assertEquals(address1.getStreet(), customer1.getAddress().getStreet());
        assertEquals(address1.getCity(), customer1.getAddress().getCity());
        assertEquals(address1.getPostalCode(), customer1.getAddress().getPostalCode());
        assertEquals(address1.getCountry(), customer1.getAddress().getCountry());

        //endregion TEST

    }

    @Test
    void getOrdersList() {
        //region TEST
        assertEquals(ordersList1.get(1), customer1.getOrdersList().get(1));
        assertEquals(ordersList1.get(3), customer1.getOrdersList().get(3));

        //endregion TEST

    }

    @Test
    void setId() {
        //region VARIABLES
        UUID uuid2 = UUID.randomUUID();

        //endregion VARIABLES


        //region TEST
        customer1.setUuid(uuid2);
        assertEquals(uuid2, customer1.getUuid());

        //endregion TEST

    }

    @Test
    void setName() {
        //region VARIABLES
        String name = "Laura";

        //endregion VARIABLES


        //region TEST
        customer1.setName(name);
        assertEquals(name, customer1.getName());

        //endregion TEST

    }

    @Test
    void setDni() {
        //region VARIABLES
        String dni = "658154785X";

        //endregion VARIABLES


        //region TEST
        customer1.setDni(dni);
        assertEquals(dni, customer1.getDni());

        //endregion TEST

    }

    @Test
    void setAddress(){
        //region VARIABLES
        AddressDTO address = AddressDTO.builder()
                .street("Cartagena, 2")
                .city("Barcelona")
                .postalCode(8023)
                .country("España").build();

        //endregion VARIABLES


        //region TEST
        customer1.getAddress().setStreet(address.getStreet());
        customer1.getAddress().setCity(address.getCity());
        customer1.getAddress().setPostalCode(address.getPostalCode());
        customer1.getAddress().setCountry(address.getCountry());

        assertEquals(address.getStreet(), customer1.getAddress().getStreet());
        assertEquals(address.getCity(), customer1.getAddress().getCity());
        assertEquals(address.getPostalCode(), customer1.getAddress().getPostalCode());
        assertEquals(address.getCountry(), customer1.getAddress().getCountry());

        //endregion TEST
    }

    @Test
    void setOrdersList() {
        //region VARIABLES
        ArrayList<Integer> ordersList = new ArrayList<>(Arrays.asList(1254, 1287, 1348, 1852, 1899, 2201, 2287, 2354));

        //endregion VARIABLES


        //region TEST
        customer1.setOrdersList(ordersList);
        assertEquals(ordersList.get(0), customer1.getOrdersList().get(0));
        assertEquals(ordersList.get(4), customer1.getOrdersList().get(4));
        assertEquals(ordersList.get(7), customer1.getOrdersList().get(7));

        //endregion TEST

    }



    //endregion TESTS


}