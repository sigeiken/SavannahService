package com.savannahinformatics.SavannahService.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CustomerSignUpRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    private String name;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    public CustomerSignUpRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        return "CustomerRequest{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
