package org.ordersMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "org.ecommerceChallenge.clients")
public class OrdersApp {
    public static void main(String[] args) {
        SpringApplication.run(OrdersApp.class,args);
    }
}