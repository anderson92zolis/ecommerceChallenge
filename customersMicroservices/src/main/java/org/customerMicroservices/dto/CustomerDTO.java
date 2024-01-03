package org.customerMicroservices.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
    //region ATTRIBUTES
    private UUID id;
    private String pass;
    private String name;
    private String dni;
    private AddressDTO address;
    private List<Integer> ordersList;

    //endregion ATTRIBUTES


}
