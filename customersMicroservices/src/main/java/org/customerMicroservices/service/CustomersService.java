package org.customerMicroservices.service;

import org.customerMicroservices.documents.CustomerDocument;
import org.customerMicroservices.dto.CustomerDTO;
import org.customerMicroservices.helpers.APPMthd;
import org.customerMicroservices.helpers.ConverterDocsAndDtos;
import org.customerMicroservices.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.kafka.listener.ConsumerAwareRebalanceListener.LOGGER;

@Service
public class CustomersService {
    //region VARIABLES
    private final HttpHeaders httpHeaders = new HttpHeaders();
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ConverterDocsAndDtos converters;

    //endregion VARIABLES


    //region METHODS: Public
    public ResponseEntity<CustomerDTO> createCustomer(CustomerDTO customerDTO) {
        //region VARIABLES
        CustomerDTO customerDTOSvd;
        CustomerDocument customerDocToSvd;
        CustomerDocument customerDocSvd;
        ResponseEntity<CustomerDTO> responseEntity;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // CHECK IF THE CUSTOMERDTO CONTAINS THE NECESSERY INFO
            if (APPMthd.isCorrectCustomerDTO(customerDTO, false)) {
                // GENERATE UUID
                customerDTO.setUuid(generatedUUID());

                // TRANSFORM FROM DTO TO DOCUMENT
                customerDocToSvd = converters.dtoToDoc(customerDTO);

                // ADD TO DDBB
                customerDocSvd = customerRepository.save(customerDocToSvd);

                // TRANSFORM FROM DOCUMENT TO DTO
                customerDTOSvd = converters.docToDto(customerDocSvd);

                //todo verification the customer is created correctly.

                // CREATE CORRECT ANSWER
                responseEntity = new ResponseEntity<>(customerDTOSvd, httpHeaders, HttpStatus.CREATED);

            } else {
                // CREATE ERROR ANSWER
                responseEntity = new ResponseEntity<>(null, httpHeaders, HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            // CREATE ERROR ANSWER
            responseEntity = new ResponseEntity<>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
            //todo info of error for log
        }

        //endregion ACTIONS


        // OUT
        return responseEntity;

    }

    public ResponseEntity<Boolean> delete(String uuid) {
        //region VARIABLES
        CustomerDocument customerDocDDBB;
        ResponseEntity<Boolean> responseEntity;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // CHECK UUID
            if (APPMthd.isValidUUID(uuid)) {
                customerDocDDBB = customerRepository.findByUuid(UUID.fromString(uuid));
                if (customerDocDDBB!=null) {
                    // DELETE CUSTOMER WITH ID
                    ////* customerRepository.deleteById(customerDocDDBB.get_id());
                    customerRepository.delete(customerDocDDBB);

                    // CREATE CORRECT ANSWER
                    responseEntity = new ResponseEntity<>(true, httpHeaders, HttpStatus.CREATED);
                }else{
                    // CREATE ERROR ANSWER
                    responseEntity = new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_FOUND);
                }
            } else {
                // CREATE ERROR ANSWER
                responseEntity = new ResponseEntity<>(null, httpHeaders, HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            // CREATE ERROR ANSWER
            responseEntity = new ResponseEntity<>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
            //todo missing error info for log
        }

        //endregion ACTIONS


        // OUT
        return responseEntity;

    }

    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        //region VARIABLES
        List<CustomerDocument> customersDocList;
        List<CustomerDTO> customersDTOList;
        ResponseEntity<List<CustomerDTO>> responseEntity;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // GET ALL CUSTOMERS FROM DDBB
            customersDocList = customerRepository.findAll();

            // TRANSFORM FROM DOCUMENT TO DTO
            customersDTOList = customersDocList.stream()
                    .map(converters::docToDto)
                    .collect(Collectors.toList());

            // CREATE CORRECT ANSWER
            responseEntity = new ResponseEntity<>(customersDTOList, httpHeaders, HttpStatus.CREATED);
        } catch (Exception ex) {
            // CREATE ERROR ANSWER
            responseEntity = new ResponseEntity<>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
            //todo info of error for log
        }

        //endregion ACTIONS


        // OUT
        return responseEntity;

    }

    public ResponseEntity<CustomerDTO> getOne(String uuid) {
        //region VARIABLES
        CustomerDTO customerDTO;
        CustomerDocument customerDoc;
        ResponseEntity<CustomerDTO> responseEntity;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // CHECK UUID
            if (APPMthd.isValidUUID(uuid)) {
                // CHECK IF UUID EXIST
                if (customerRepository.existsByUuid(UUID.fromString(uuid))) {
                    // GET CUSTOMER
                    customerDoc = customerRepository.findByUuid(UUID.fromString(uuid));

                    // TRANSFORM FROM DOCUMENT TO DTO
                    customerDTO = converters.docToDto(customerDoc);

                    // CREATE CORRECT ANSWER
                    responseEntity = new ResponseEntity<>(customerDTO, httpHeaders, HttpStatus.CREATED);
                } else {
                    // CREATE ERROR ANSWER
                    responseEntity = new ResponseEntity<>(null, httpHeaders, HttpStatus.CONFLICT);
                }
            } else {
                // CREATE ERROR ANSWER
                responseEntity = new ResponseEntity<>(null, httpHeaders, HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            // CREATE ERROR ANSWER
            responseEntity = new ResponseEntity<>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
            //todo info of error for log
        }
        //endregion ACTIONS


        // OUT
        return responseEntity;

    }

    public ResponseEntity<CustomerDTO> update(CustomerDTO customerDTO) {
        //region VARIABLES
        CustomerDTO customerDTOSvd;
        CustomerDocument customerDocToSvd;
        CustomerDocument customerDocSvd;
        CustomerDocument customerDocDDBB;
        ResponseEntity<CustomerDTO> responseEntity;

        //endregion VARIABLES


        //region ACTIONS
        try {
            // CHECK IF THE CUSTOMERDTO CONTAINS THE NECESSERY INFO
            if (APPMthd.isCorrectCustomerDTO(customerDTO, true)) {
                // CHECK IF CUSTOMER EXIST
                customerDocDDBB = customerRepository.findByUuid(customerDTO.getUuid());
                if (customerDocDDBB!=null) {
                    // TRANSFORM FROM DTO TO DOCUMENT
                    customerDocToSvd = converters.dtoToDoc(customerDTO);
                    customerDocToSvd.set_id(customerDocDDBB.get_id());

                    // MODIFY ELEMENT TO DDBB
                    customerDocSvd = customerRepository.save(customerDocToSvd);

                    // TRANSFORM FROM DOCUMENT TO DTO
                    customerDTOSvd = converters.docToDto(customerDocSvd);

                    // CREATE CORRECT ANSWER
                    responseEntity = new ResponseEntity<>(customerDTOSvd, httpHeaders, HttpStatus.CREATED);

                } else {
                    // CREATE ERROR ANSWER
                    responseEntity = new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_FOUND);
                }
            } else {
                // CREATE ERROR ANSWER
                responseEntity = new ResponseEntity<>(null, httpHeaders, HttpStatus.NO_CONTENT);
            }
        } catch (
                Exception ex) {
            // CREATE ERROR ANSWER
            responseEntity = new ResponseEntity<>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
            //todo info of error for log
        }

        //endregion ACTIONS


        // OUT
        return responseEntity;

    }

    //endregion METHODS: Public


    //region METHODS: Private
    private UUID generatedUUID() {
        //region VARIABLES
        boolean exitDo;
        UUID uuid = null;

        //endregion VARIABLES


        //region ACTIONS
        try {
            do {
                // GENERATE UUID
                uuid = UUID.randomUUID();
                // CHECK IF UUID EXIST
                exitDo = !customerRepository.existsByUuid(uuid);
            } while (!exitDo);
        } catch (Exception ex) {
            //todo error log info
        }

        //endregion ACTIONS


        // OUT
        return uuid;

    }


    //endregion METHODS: Private


}
