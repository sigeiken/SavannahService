package com.savannahinformatics.SavannahService.controller;

import com.savannahinformatics.SavannahService.security.JwtUtil;
import com.savannahinformatics.SavannahService.service.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = ApiRequestController.class)
public class ApiRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private AuthService authService;

    @MockBean
    private MessageService messageService;

    @MockBean
    private JwtUtil jwtUtil;

    @WithMockUser("spring")
    @Test
    @DisplayName("Should add new order new order when making POST request to - /savannah/api/v1/addOrder")
    public void shouldAddNewOrder() throws Exception{
        String json = "{\n" +
                "    \"item\":\"iphone 12\",\n" +
                "    \"amount\": \"120000\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/savannah/api/v1/addOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect( MockMvcResultMatchers.status().is(200))
                .andReturn();
    }

    @Test
    public void failingTest() throws Exception{
        assertEquals(4, 2+2);
    }
}