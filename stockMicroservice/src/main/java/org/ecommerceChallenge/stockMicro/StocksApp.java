package org.ecommerceChallenge.stockMicro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StocksApp {
    public static void main(String[] args) {
        SpringApplication.run(StocksApp.class,args);

    }
}