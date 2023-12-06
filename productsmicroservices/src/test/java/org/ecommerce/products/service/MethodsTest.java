package org.ecommerce.products.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MethodsTest {

    @Test
    void subtract() {
        Methods methods = new Methods();

        assertNotEquals(null, methods, "Good");

        assertEquals(-1, Methods.subtract(2,3), "All good");
        assertNotEquals(1, Methods.suma(3,3), "Bad");
    }

    @Test
    void suma() {
        assertEquals(5, Methods.suma(2,3), "All good");
        assertNotEquals(5, Methods.suma(3,3), "Bad");
    }

    @Test
    void suma3() {
        assertEquals(8, Methods.suma3(2,3, 3), "All good");
        assertNotEquals(5, Methods.suma3(3,3, 3), "Bad");
    }
}