package org.ecommerceChallenge.notification;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class NotificationsApp {

    public static void main(String[] args) {
        SpringApplication.run(NotificationsApp.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
        return rgs -> {
            for (int i = 0; i < 10; i++) {
                kafkaTemplate.send("notificationEcommerce", "hello kafka :"+i);
            }
        };
    }

}


