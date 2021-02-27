package com.savannahinformatics.SavannahService.controller;

import com.savannahinformatics.SavannahService.request.CustomerSignUpRequest;
import com.savannahinformatics.SavannahService.request.OrderRequest;
import com.savannahinformatics.SavannahService.service.AuthService;
import com.savannahinformatics.SavannahService.service.CustomerService;
import com.savannahinformatics.SavannahService.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/savannah/api/v1/")
public class ApiRequestController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthService authService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiRequestController.class);

    @PostMapping("/addOrder")
    public ResponseEntity<?> addOrder(@RequestBody OrderRequest orderRequest){
        LOGGER.info("POST | /api/v1/savannah/addOrder {}", orderRequest);
        Map<String, Object> responseMap = new LinkedHashMap<>();
        try {
            orderService.addOrder(orderRequest);
            responseMap.put("responseCode", "00");
            responseMap.put("responseMessage", "Success");
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        }catch (Exception e){
            responseMap.put("responseCode", "01");
            responseMap.put("responseMessage", e.getMessage());
            LOGGER.info("POST | /api/v1/savannah/addOrder responseMap= {}", responseMap);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        }
    }
}
