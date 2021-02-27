package com.savannahinformatics.SavannahService.service;

import com.savannahinformatics.SavannahService.entity.Customer;
import com.savannahinformatics.SavannahService.repository.CustomerRepository;
import com.savannahinformatics.SavannahService.request.CustomerSignUpRequest;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public void addCustomer(CustomerSignUpRequest customerRequest) throws Exception {
        try {
            Customer customer = new Customer();
            customer.setFirstName(customerRequest.getFirstName());
            customer.setLastName(customerRequest.getLastName());
            customer.setPhone(customerRequest.getPhone());
            customer.setCode(generateCustomerCode());
            customer.setCreatedAt(Instant.now());
            customer.setUpdatedAt(Instant.now());

            customerRepository.save(customer);
        }catch (Exception e){
            LOGGER.error("Error occurred adding new customer");
            throw new Exception("Error occurred " + e.getMessage());
        }
    }

    private String generateCustomerCode() {
        String generatedString = RandomStringUtils.randomAlphanumeric(10);
        return generatedString;
    }
}
