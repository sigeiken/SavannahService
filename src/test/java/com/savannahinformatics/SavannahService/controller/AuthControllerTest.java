package com.savannahinformatics.SavannahService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savannahinformatics.SavannahService.entity.Customer;
import com.savannahinformatics.SavannahService.repository.CustomerRepository;
import com.savannahinformatics.SavannahService.request.CustomerSignUpRequest;
import com.savannahinformatics.SavannahService.request.LoginRequest;
import com.savannahinformatics.SavannahService.response.LoginResponse;
import com.savannahinformatics.SavannahService.response.Response;
import com.savannahinformatics.SavannahService.security.JwtUtil;
import com.savannahinformatics.SavannahService.service.AuthService;
import com.savannahinformatics.SavannahService.service.CustomUserDetailsService;
import com.savannahinformatics.SavannahService.service.RefreshTokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc; //provides a way of calling apis

    @MockBean
    private AuthService authService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private RefreshTokenService refreshTokenService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Should add new customer to the database when making POST request to - /savannah/api/v1/auth/signup")
    public void shouldAddNewCustomer() throws Exception{
        CustomerSignUpRequest request = new CustomerSignUpRequest();
        request.setEmail("kennethsigei31@gmail.com");
        request.setName("Kenneth Sigei");
        request.setPassword("123");
        request.setPhone("254727053832");

        assertEquals(request.getEmail(), "kennethsigei31@gmail.com");

        Mockito.doNothing().when(authService).signUp(request);
        mockMvc.perform(MockMvcRequestBuilders.post("/savannah/api/v1/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @DisplayName("Should allow customer to login to the system when making POST request to - /savannah/api/v1/auth/login")
    public void shouldLoginCustomer() throws Exception{
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("kennethsigei31@gmail.com");
        loginRequest.setPassword("123");

        assertEquals(loginRequest.getEmail(), "kennethsigei31@gmail.com");
        assertEquals(loginRequest.getPassword(), "123");

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String token = jwtUtil.generateToken(userDetails);

        String res = "{\n" +
                "    \"responseCode\": \"00\",\n" +
                "    \"responseMessage\": \"Success\",\n" +
                "    \"data\": {\n" +
                "        \"authenticationToken\": \"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrZW5uZXRoc2lnZWkzMEBnbWFpbC5jb20iLCJpYXQiOjE2MTM3MjA2OTAsImV4cCI6MTYxMzcyMTU5MH0.PWJPVYEB9y4oT_AGYMTsQZ3ZPUD_B-UYSEIgcWk1wN8\",\n" +
                "        \"refreshToken\": \"4fec82fe-fde6-489e-87a3-9b2e4b866b42\",\n" +
                "        \"expiresAt\": 1613721592.043276000,\n" +
                "        \"username\": \"kennethsigei30@gmail.com\"\n" +
                "    }\n" +
                "}";

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAuthenticationToken(token);
//        loginResponse.setRefreshToken(refreshTokenService.generateRefreshToken().getToken());
        loginResponse.setExpiresAt(Instant.now().plusMillis(jwtUtil.getJwtExpirationInMillis()));
        loginResponse.setUsername(loginRequest.getEmail());

        Mockito.when(authService.login(Mockito.any())).thenReturn(loginResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/savannah/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(loginRequest)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }
}