package org.ordersMicroservice.helper;

import org.modelmapper.ModelMapper;
import org.ordersMicroservice.dto.OrderDto;
import org.ordersMicroservice.dto.verify.OrderDetailDocumentVerifiedDto;
import org.ordersMicroservice.dto.verify.OrderVerifiedDto;
import org.ordersMicroservice.entity.OrderDetailDocument;
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

        OrderDto orderDto = modelMapper().map(orderDocument, OrderDto.class);
        return orderDto;
    }


    // to verified

    public OrderDetailDocumentVerifiedDto orderDetailsDocumentsToOrderDetailsDocumentVerifiedDto (OrderDetailDocument orderDetailDocument){

        OrderDetailDocumentVerifiedDto orderDetailDtoDocumentVerified = modelMapper().map(orderDetailDocument, OrderDetailDocumentVerifiedDto.class);
        return orderDetailDtoDocumentVerified;
    }





}
