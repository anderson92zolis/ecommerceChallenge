package org.customerMicroservices.service;

import lombok.Setter;
import org.apache.juli.logging.Log;
import org.customerMicroservices.documents.CustomerDocument;
import org.customerMicroservices.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.kafka.listener.ConsumerAwareRebalanceListener.LOGGER;

@Service
public class CustomersService {
    @Autowired
    private CustomerRepository customerRepository;


    public CustomerDocument createCustomer(CustomerDocument customer){
        return customerRepository.save(customer);
    }


    public List<CustomerDocument> getAllCustomers(){
        try {
            // INITIALIZATION
            return customerRepository.findAll();

        } catch (Exception ex) {
            LOGGER.info("eComerce: " + ex.getMessage());
        }
        return null;
    }

}
