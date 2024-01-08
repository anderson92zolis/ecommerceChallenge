package org.customerMicroservices.documents;

import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.ObjectId;
import org.customerMicroservices.dto.AddressDTO;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
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
    private ObjectId _id;
    private UUID uuid;
    private String pass;
    private String name;
    private String dni;
    private AddressDTO address;
    private List<Integer> ordersList;

    //endregion ATTRIBUTES

}
