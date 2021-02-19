package com.savannahinformatics.SavannahService.controller;

import com.savannahinformatics.SavannahService.request.OrderRequest;
import com.savannahinformatics.SavannahService.service.AuthService;
import com.savannahinformatics.SavannahService.service.MessageService;
import com.savannahinformatics.SavannahService.service.OrderService;
import com.savannahinformatics.SavannahService.service.OrderServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = ApiRequestController.class)
public class ApiRequestControllerTest {

    @MockBean //Inject dependencies for Orderservice class
    private OrderServiceImpl orderService;

    @MockBean
    private MessageService messageService;

    @MockBean
    private AuthService authService;

    @Autowired
    private MockMvc mockMvc; //provides a way of calling apis

    @Test
    @DisplayName("Should add an order to the database when making POST request to - /savannah/api/v1/addOrder")
    public void shouldAddAnOrder() throws Exception{
//        String json = "{\n" +
//                "    \"item\":\"iphone 12\",\n" +
//                "    \"amount\": \"120000\"\n" +
//                "}";
        OrderRequest json = new OrderRequest();
        json.setItem("iphone 12");
        json.setAmount(new BigDecimal(120000));
        mockMvc.perform(MockMvcRequestBuilders.post("/savannah/api/v1/addOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(json)))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }
}