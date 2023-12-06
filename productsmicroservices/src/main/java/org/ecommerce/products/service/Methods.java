package org.ecommerce.products.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Methods {
    public static int suma(int a, int b) {
        int result;
        result = a + b;

        return result;
    }

    public static int subtract (int a, int b) {
        int result;
        result = a - b;

        return result;
    }
}
