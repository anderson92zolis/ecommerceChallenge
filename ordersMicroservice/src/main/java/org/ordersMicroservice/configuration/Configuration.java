package org.ordersMicroservice.configuration;

import org.springframework.web.reactive.function.client.WebClient;

@org.springframework.context.annotation.Configuration

public class Configuration {

    WebClient webClient(WebClient.Builder builder){
        return builder.build();
    }
}
