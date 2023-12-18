package org.customerMicroservices.service;

import lombok.Setter;
import org.customerMicroservices.documents.CustomerDocument;
import org.customerMicroservices.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersService {
    @Autowired
    private CustomerRepository customerRepository;


    public CustomerDocument createCustomer(CustomerDocument customer){
        return customerRepository.save(customer);
    }


    public List<CustomerDocument> getAllCustomers(){
        return customerRepository.findAll();
    }

}
