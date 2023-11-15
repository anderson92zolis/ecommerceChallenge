package org.ecommerceChallenge.notification;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {


    @KafkaListener(
            topics="notificationEcommerce",
            groupId= "groudId"
    )  void listener(String data){
        System.out.println("Listener recived" + data + "😉");
    }


}
