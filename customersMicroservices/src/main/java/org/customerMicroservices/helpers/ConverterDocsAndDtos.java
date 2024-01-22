package org.customerMicroservices.helpers;

import org.customerMicroservices.documents.CustomerDocument;
import org.customerMicroservices.dto.CustomerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//// @Component
public class ConverterDocsAndDtos {
    //region METHODS: Bean
//    @Bean
//    public ModelMapper modelMaper(){
//        return new ModelMapper();
//    }

    private static ModelMapper modelMaper(){
        return new ModelMapper();
    }

    //endregion METHODS: Bean


    //region METHODS: Public
    public static CustomerDTO docToDto(CustomerDocument customerDocument){
        return modelMaper().map(customerDocument, CustomerDTO.class);
    }

    public static CustomerDocument dtoToDoc(CustomerDTO customerDTO){
        return modelMaper().map(customerDTO, CustomerDocument.class);
    }

    //endregion METHODS: Public

}
