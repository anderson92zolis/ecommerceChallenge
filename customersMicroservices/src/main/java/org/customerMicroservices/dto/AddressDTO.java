package org.customerMicroservices.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {
    //region ATTRIBUTES
    private String street;
    private String city;
    private int postalCode;
    private String country;

    //endregion ATTRIBUTES

}
