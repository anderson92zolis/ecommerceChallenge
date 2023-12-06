package org.ecommerce.products.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Methods {
    public static int suma(int a, int b) {
        int result;
        result = a + b;

        return result;
    }

    public static int suma3(int a, int b, int c) {
        int result;
        result = a + b + c;

        return result;
    }

    public static int subtract (int a, int b) {
        int result;
        result = a - b;

        return result;
    }
}
