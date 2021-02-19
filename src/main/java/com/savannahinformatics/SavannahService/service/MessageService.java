package com.savannahinformatics.SavannahService.service;

public interface MessageService {
    void sendSms(Long id, String recipient, String message) throws Exception;
}
