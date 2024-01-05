package org.customerMicroservices.repository;

import org.customerMicroservices.documents.CustomerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerDocument, UUID> {

    boolean existsByUuid(UUID uuid);
    CustomerDocument findByUuid(UUID uuid);

}
