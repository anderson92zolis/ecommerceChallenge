package org.ordersMicroservice.helper;

import org.modelmapper.ModelMapper;
import org.ordersMicroservice.dto.OrderDto;
import org.ordersMicroservice.entity.OrderDocument;
import org.springframework.context.annotation.Bean;

public class Converter {

    @Bean
    public ModelMapper modelMapper1() {
        return new ModelMapper();
    }

    public OrderDto entityToDto (OrderDocument orderDocument){

        OrderDto orderDto = modelMapper1().map(OrderDocument.class, OrderDto.class);
        return orderDto;
    }

}
