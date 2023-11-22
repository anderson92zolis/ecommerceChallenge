package org.ecommerceChallenge.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationsApp {

    public static void main(String[] args) {
        SpringApplication.run(NotificationsApp.class, args);
    }

}


