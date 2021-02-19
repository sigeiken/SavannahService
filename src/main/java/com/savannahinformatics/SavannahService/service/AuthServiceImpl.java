package com.savannahinformatics.SavannahService.service;

import com.savannahinformatics.SavannahService.entity.Customer;
import com.savannahinformatics.SavannahService.repository.CustomerRepository;
import com.savannahinformatics.SavannahService.request.CustomerSignUpRequest;
import com.savannahinformatics.SavannahService.request.LoginRequest;
import com.savannahinformatics.SavannahService.response.LoginResponse;
import com.savannahinformatics.SavannahService.security.JwtUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Override
    public void signUp(CustomerSignUpRequest customerSignUpRequest) throws Exception {
        try {
            Customer customer = new Customer();
            customer.setName(customerSignUpRequest.getName());
            customer.setEmail(customerSignUpRequest.getEmail());
            customer.setPhone(customerSignUpRequest.getPhone());
            customer.setPassword(passwordEncoder.encode(customerSignUpRequest.getPassword()));
            customer.setCode(generateCustomerCode());
            customer.setCreatedAt(Instant.now());
            customer.setUpdatedAt(Instant.now());

            customerRepository.save(customer);
        }catch (Exception e){
            throw new Exception("Error occurred " + e);
        }
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            final UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
            final String token = jwtUtil.generateToken(userDetails);

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setAuthenticationToken(token);
            loginResponse.setRefreshToken(refreshTokenService.generateRefreshToken().getToken());
            loginResponse.setExpiresAt(Instant.now().plusMillis(jwtUtil.getJwtExpirationInMillis()));
            loginResponse.setUsername(loginRequest.getEmail());
            return loginResponse;
        }catch (Exception e){
            throw new Exception("Error occurred " + e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getCurrentUser() throws Exception {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = auth.getName();
            return customerRepository.findCustomerByEmail(email) .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + email));
        }catch (Exception e){
            throw new Exception("Error occurred " + e);
        }
    }

    private String generateCustomerCode() {
        String generatedString = RandomStringUtils.randomAlphanumeric(10);
        return generatedString;
    }
}
