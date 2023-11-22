package org.ordersMicroservice.eventsKafka.entity;

import org.ordersMicroservice.eventsKafka.enums.OrderStatus;


public record Message(String message, int productId, OrderStatus orderStatus) {
}
