package com.savannahinformatics.SavannahService.controller;

import com.savannahinformatics.SavannahService.repository.CustomerRepository;
import com.savannahinformatics.SavannahService.security.JwtUtil;
import com.savannahinformatics.SavannahService.service.AuthService;
import com.savannahinformatics.SavannahService.service.CustomUserDetailsService;
import com.savannahinformatics.SavannahService.service.MessageService;
import com.savannahinformatics.SavannahService.service.RefreshTokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

    @Test
    @DisplayName("Should add new customer to the database when making POST request to - /savannah/api/v1/auth/signup")
    public void shouldSignUpNewCustomer() throws Exception{
        String json = "{\n" +
                "    \"name\": \"Kenneth Kipkoech\",\n" +
                "    \"phone\":\"254727053832\",\n" +
                "    \"email\":\"kennethsigei31@gmail.com\",\n" +
                "    \"password\":\"123\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/savannah/api/v1/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect( MockMvcResultMatchers.status().is(200))
                .andReturn();
    }

    @Test
    @DisplayName("Should allow customer to login when making POST request to - /savannah/api/v1/auth/login")
    public void shouldAllowCustomerToLogin() throws Exception{
        String json = "{\n" +
                "    \"email\":\"kennethsigei30@gmail.com\",\n" +
                "    \"password\":\"13\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/savannah/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect( MockMvcResultMatchers.status().is(200))
                .andReturn();
    }
}