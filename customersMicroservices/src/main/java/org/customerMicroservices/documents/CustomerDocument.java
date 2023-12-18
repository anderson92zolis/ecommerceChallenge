package org.customerMicroservices.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customers")
public class CustomerDocument {
    //region ATTRIBUTES
    @Id
    @Field
    private UUID uuid;

    @Field(name="name")
    private String name;

    //endregion ATTRIBUTES

}



