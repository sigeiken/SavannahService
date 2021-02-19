package com.savannahinformatics.SavannahService.service;

import com.savannahinformatics.SavannahService.request.CustomerSignUpRequest;

public interface CustomerService {
    void addCustomer(CustomerSignUpRequest customerRequest) throws Exception;
}
