package org.customerMicroservices.documents;

import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customers")
public class CustomerDocument {
    //region ATTRIBUTES
    ////* @Field
    @Id
    private ObjectId _id;
    private UUID uuid;

    ////* @Field(name="name")
    private String name;

    //endregion ATTRIBUTES

}
