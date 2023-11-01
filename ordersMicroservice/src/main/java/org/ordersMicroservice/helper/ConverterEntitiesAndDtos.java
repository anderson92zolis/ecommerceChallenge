package org.ordersMicroservice.helper;

import org.modelmapper.ModelMapper;
import org.ordersMicroservice.dto.OrderDto;
import org.ordersMicroservice.entity.OrderDocument;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConverterEntitiesAndDtos {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public OrderDto entityToDto (OrderDocument orderDocument){

        OrderDto orderDto = modelMapper().map(OrderDocument.class, OrderDto.class);
        return orderDto;
    }

}
