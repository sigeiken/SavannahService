package com.savannahinformatics.SavannahService.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    public LoginRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
