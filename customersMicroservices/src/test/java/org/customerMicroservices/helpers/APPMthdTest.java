package org.customerMicroservices.helpers;

import org.customerMicroservices.dto.AddressDTO;
import org.customerMicroservices.dto.CustomerDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.expression.spel.ast.NullLiteral;
import org.testcontainers.shaded.org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class APPMthdTest {
    //region TESTS
    @Test
    void isCorrectCustomerDTO() {
        //region VARIABLES
        UUID uuid1;
        ArrayList<Integer> ordersList1;
        AddressDTO address1;
        CustomerDTO customerGood1, customerWrong1, customerWrong2, customerWrong3, customerWrong4, customerWrong5,
                customerWrong6, customerWrong7;

        //endregion VARIABLES


        //region TEST INITIALIZATION
        uuid1 = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479");
        ordersList1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 6, 7, 8));
        address1 = AddressDTO.builder()
                .street("Cartagena, 2")
                .city("Barcelona")
                .postalCode(8023)
                .country("España").build();
        // CUSTOMER GOODS
        customerGood1 = CustomerDTO.builder()
                .uuid(uuid1)
                .pass("fernandoPass")
                .name("Fernando")
                .dni("78124592D")
                .address(address1)
                .ordersList(ordersList1).build();
        // CUSTOMER WRONGS
        customerWrong1 = null;
        customerWrong2 = new CustomerDTO();
        customerWrong3 = CustomerDTO.builder()
                .uuid(null)
                .pass("fernandoPass")
                .name("Fernando")
                .dni("78124592D")
                .address(address1)
                .ordersList(ordersList1).build();
        customerWrong4 = CustomerDTO.builder()
                .uuid(uuid1)
                .pass("")
                .name("Fernando")
                .dni("78124592D")
                .address(address1)
                .ordersList(ordersList1).build();
        customerWrong5 = CustomerDTO.builder()
                .uuid(uuid1)
                .pass("   ")
                .name("Fernando")
                .dni("78124592D")
                .address(address1)
                .ordersList(ordersList1).build();
        customerWrong6 = CustomerDTO.builder()
                .uuid(uuid1)
                .pass("fernandoPass")
                .name("")
                .dni("78124592D")
                .address(address1)
                .ordersList(ordersList1).build();
        customerWrong7 = CustomerDTO.builder()
                .uuid(uuid1)
                .pass("fernandoPass")
                .name("Fernando")
                .dni("")
                .address(address1)
                .ordersList(ordersList1).build();

        //endregion TEST INITIALIZATION


        //region TEST
        assertTrue(APPMthd.isCorrectCustomerDTO(customerGood1, true));
        assertFalse(APPMthd.isCorrectCustomerDTO(customerWrong1, true));
        assertFalse(APPMthd.isCorrectCustomerDTO(customerWrong2, true));
        assertFalse(APPMthd.isCorrectCustomerDTO(customerWrong3, true));
        assertFalse(APPMthd.isCorrectCustomerDTO(customerWrong4, true));
        assertFalse(APPMthd.isCorrectCustomerDTO(customerWrong5, true));
        assertFalse(APPMthd.isCorrectCustomerDTO(customerWrong6, true));
        assertFalse(APPMthd.isCorrectCustomerDTO(customerWrong7, true));


        //endregion TEST

    }

    @Test
    void isValidDNI() {
        //region VARIABLES
        // Goods DNI
        String dniGood1 = "46140604m";
        String dniGood2 = "46140604M";
        String dniGood3 = "12345678Z";
        String dniGood4 = "98765431G";
        String dniGood5 = "87654321X";
        String dniGood6 = "00000024R";
        // Bads DNI
        String dniBad1 = null;
        String dniBad2 = "";
        String dniBad3 = "46140604";
        String dniBad4 = "6140604";
        String dniBad5 = "6140604M";
        String dniBad6 = "EDR0604M";

        //endregion VARIABLES


        //region TEST INITIALIZATION


        //endregion TEST INITIALIZATION


        //region TEST
        // Goods DNI
        assertTrue(APPMthd.isValidDNI(dniGood1));
        assertTrue(APPMthd.isValidDNI(dniGood2));
        assertTrue(APPMthd.isValidDNI(dniGood3));
        assertTrue(APPMthd.isValidDNI(dniGood4));
        assertTrue(APPMthd.isValidDNI(dniGood5));
        assertTrue(APPMthd.isValidDNI(dniGood6));
        // Bad DNI
        assertFalse(APPMthd.isValidDNI(dniBad1));
        assertFalse(APPMthd.isValidDNI(dniBad2));
        assertFalse(APPMthd.isValidDNI(dniBad3));
        assertFalse(APPMthd.isValidDNI(dniBad4));
        assertFalse(APPMthd.isValidDNI(dniBad5));
        assertFalse(APPMthd.isValidDNI(dniBad6));

        //endregion TEST


    }

    @Test
    void isValidEmail() {
        //region VARIABLES
        // VALID EMAIL
        String emailGood1 = "customer1@ecomerce.com";

        // WRONG EMAIL
        String emailWrong1 = null;
        String emailWrong2 = "";
        String emailWrong3 = " ";
        String emailWrong4 = "@ecomerce.com";
        String emailWrong5 = "ecomerce.com";
        String emailWrong6 = "customer1ecomerce.com";
        String emailWrong7 = "customer1@.com";
        String emailWrong8 = "customer1@ecomercecom";
        String emailWrong9 = "customer1@ecomerce.";
        String emailWrong10 = "customer1.com";
        String emailWrong11 = "customer1";
        String emailWrong12 = ".com";
        String emailWrong13 = "sdfsdfasdf";

        //endregion VARIABLES


        //region TEST
        assertTrue(APPMthd.isValidEmail(emailGood1));
        assertFalse(APPMthd.isValidEmail(emailWrong1));
        assertFalse(APPMthd.isValidEmail(emailWrong2));
        assertFalse(APPMthd.isValidEmail(emailWrong3));
        assertFalse(APPMthd.isValidEmail(emailWrong4));
        assertFalse(APPMthd.isValidEmail(emailWrong5));
        assertFalse(APPMthd.isValidEmail(emailWrong6));
        assertFalse(APPMthd.isValidEmail(emailWrong7));
        assertFalse(APPMthd.isValidEmail(emailWrong8));
        assertFalse(APPMthd.isValidEmail(emailWrong9));
        assertFalse(APPMthd.isValidEmail(emailWrong10));
        assertFalse(APPMthd.isValidEmail(emailWrong11));
        assertFalse(APPMthd.isValidEmail(emailWrong12));
        assertFalse(APPMthd.isValidEmail(emailWrong13));

        //endregion TEST

    }

    @Test
    void isValidUUID() {
        //region VARIABLES
        // VALID UUID
        UUID uuidGood1 = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479");

        // WRONG UUID
        String uuidWrong1 = null;
        String uuidWrong2 = "";
        String uuidWrong3 = "   ";
        String uuidWrong4 = "adfasdfasdf";
        String uuidWrong5 = "47ac10b-58cc-4372-a567-0e02b2c3d479";
        String uuidWrong6 = "-58cc-4372-a567-0e02b2c3d479";
        String uuidWrong7 = "f47ac10b-58c-4372-a567-0e02b2c3d479";
        String uuidWrong8 = "f47ac10b--4372-a567-0e02b2c3d479";
        String uuidWrong9 = "f47ac10b-58cc-432-a567-0e02b2c3d479";
        String uuidWrong10 = "f47ac10b-58cc--a567-0e02b2c3d479";
        String uuidWrong11 = "f47ac10b-58cc-4372-567-0e02b2c3d479";
        String uuidWrong12 = "f47ac10b-58cc-4372--0e02b2c3d479";
        String uuidWrong13 = "f47ac10b-58cc-4372-a567-0e02b2c3d47";
        String uuidWrong14 = "f47ac10b-58cc-4372-a567-0e02b2c";
        String uuidWrong15 = "f47ac10b-58cc-4372-a567-";
        String uuidWrong16 = "-58cc--a567-";
        String uuidWrong17 = "----";

        //endregion VARIABLES


        //region TEST
        assertTrue(APPMthd.isValidUUID(uuidGood1.toString()));
        assertFalse(APPMthd.isValidUUID(uuidWrong1));
        assertFalse(APPMthd.isValidUUID(uuidWrong2));
        assertFalse(APPMthd.isValidUUID(uuidWrong3));
        assertFalse(APPMthd.isValidUUID(uuidWrong4));
        assertFalse(APPMthd.isValidUUID(uuidWrong5));
        assertFalse(APPMthd.isValidUUID(uuidWrong6));
        assertFalse(APPMthd.isValidUUID(uuidWrong7));
        assertFalse(APPMthd.isValidUUID(uuidWrong8));
        assertFalse(APPMthd.isValidUUID(uuidWrong9));
        assertFalse(APPMthd.isValidUUID(uuidWrong10));
        assertFalse(APPMthd.isValidUUID(uuidWrong11));
        assertFalse(APPMthd.isValidUUID(uuidWrong12));
        assertFalse(APPMthd.isValidUUID(uuidWrong13));
        assertFalse(APPMthd.isValidUUID(uuidWrong14));
        assertFalse(APPMthd.isValidUUID(uuidWrong15));
        assertFalse(APPMthd.isValidUUID(uuidWrong16));
        assertFalse(APPMthd.isValidUUID(uuidWrong17));

        //endregion TEST

    }

    //endregion TESTS

}