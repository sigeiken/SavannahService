package com.savannahinformatics.SavannahService.service;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import com.africastalking.sms.Recipient;
import com.savannahinformatics.SavannahService.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private Environment environment;

    @Autowired
    private MessageRepository messageRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Override
    public void sendSms(Long id, String recipient, String message) throws Exception {
        //Call Twilio
//        try {
//            Twilio.init(environment.getRequiredProperty("twilio.ACCOUNT_SID"), environment.getRequiredProperty("twilio.AUTH_TOKEN"));
//            Message msg = Message
//                    .creator(new PhoneNumber("+" + recipient), // to
//                            new PhoneNumber("+15153258821"), // from
//                            message)
//                    .create();
//            messageRepository.updateMessageStatusAndDescription(id, "1", "DELIVERED");
//            LOGGER.info("Message sent to Phone Number {}, Message {}", recipient, message);
//        }catch (Exception e){
//            throw new Exception("Error occurred " + e);
//        }

        //Call Africa's talking
        String userName = environment.getRequiredProperty("africastalking.username");
        String apiKey = environment.getRequiredProperty("africastalking.apikey");

        AfricasTalking.initialize(userName, apiKey);
        /* Get the SMS service */
        SmsService sms = AfricasTalking.getService(AfricasTalking.SERVICE_SMS);
        /* Set the numbers you want to send to in international format */
        String[] recipients = new String[] {"+" + recipient};
        /* Set your shortCode or senderId */
        String from = "Savannah"; // or "ABCDE"
        try {
            List<Recipient> response = sms.send(message, from, recipients, true);
            for (Recipient r : response) {
                LOGGER.info(r.number);
                LOGGER.info(" : ");
                LOGGER.info(r.status);
                if (r.status.equals("Success")){
                    messageRepository.updateMessageStatusAndDescription(id, "1", "DELIVERED");
                    LOGGER.info("Message sent to Phone Number {}, Message {}", recipient, message);
                }else {
                    messageRepository.updateMessageStatusAndDescription(id, "0", "FAILED"); // Will retry...
                    LOGGER.info("Failed to sent message to Phone Number {}, Message {}", recipient, message);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("Error occurred " + e);
        }

    }
}
