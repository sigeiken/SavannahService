package com.savannahinformatics.SavannahService.service;

import com.savannahinformatics.SavannahService.entity.Customer;
import com.savannahinformatics.SavannahService.request.CustomerSignUpRequest;
import com.savannahinformatics.SavannahService.request.LoginRequest;
import com.savannahinformatics.SavannahService.response.LoginResponse;

public interface AuthService {
    Customer getCurrentUser() throws Exception;
    void signUp(CustomerSignUpRequest customerSignUpRequest) throws Exception;
    LoginResponse login(LoginRequest loginRequest) throws Exception;
}
