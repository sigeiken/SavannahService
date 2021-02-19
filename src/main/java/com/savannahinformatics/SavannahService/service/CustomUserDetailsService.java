package com.savannahinformatics.SavannahService.service;

import com.savannahinformatics.SavannahService.entity.Customer;
import com.savannahinformatics.SavannahService.repository.CustomerRepository;
import com.savannahinformatics.SavannahService.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customerEntity = customerRepository.findCustomerByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email + " Not Found"));
        if (customerEntity == null)
            throw new UsernameNotFoundException(email);
        return new UserPrincipal(customerEntity);
    }
}
