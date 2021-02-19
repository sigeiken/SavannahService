package com.savannahinformatics.SavannahService.response;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 1L;
    private String responseCode;
    private String responseMessage;

    public Response() {
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Override
    public String toString() {
        return "Response{" +
                "responseCode='" + responseCode + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                '}';
    }
}
