package com.savannahinformatics.SavannahService.controller;

import com.savannahinformatics.SavannahService.request.CustomerSignUpRequest;
import com.savannahinformatics.SavannahService.request.LoginRequest;
import com.savannahinformatics.SavannahService.response.LoginResponse;
import com.savannahinformatics.SavannahService.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @PostMapping(value = "/signup", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> signUp(@RequestBody CustomerSignUpRequest customerSignUpRequest){
        LOGGER.info("POST | /api/v1/auth/signup {}", customerSignUpRequest);
        Map<String, Object> responseMap = new LinkedHashMap<>();
        try {
            authService.signUp(customerSignUpRequest);
            responseMap.put("responseCode", "00");
            responseMap.put("responseMessage", "Success");
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        }catch (Exception e){
            responseMap.put("responseCode", "01");
            responseMap.put("responseMessage", e.getMessage());
            LOGGER.info("POST | /api/v1/auth/signup responseMap= {}", responseMap);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        LOGGER.info("POST | /api/v1/auth/login {}", loginRequest);
        Map<String, Object> responseMap = new LinkedHashMap<>();
        try {
            LoginResponse loginResponse = authService.login(loginRequest);
            responseMap.put("responseCode", "00");
            responseMap.put("responseMessage", "Success");
            responseMap.put("data", loginResponse);
            LOGGER.info("POST | /api/v1/auth/login response= {}", responseMap);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        }catch (Exception e){
            responseMap.put("responseCode", "01");
            responseMap.put("responseMessage", e.getMessage());
            LOGGER.info("POST | /api/v1/auth/login responseMap= {}", responseMap);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        }
    }

}
