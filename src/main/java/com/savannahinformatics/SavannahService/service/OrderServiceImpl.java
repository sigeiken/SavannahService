package com.savannahinformatics.SavannahService.service;

import com.savannahinformatics.SavannahService.entity.Customer;
import com.savannahinformatics.SavannahService.entity.Message;
import com.savannahinformatics.SavannahService.entity.Order;
import com.savannahinformatics.SavannahService.repository.CustomerRepository;
import com.savannahinformatics.SavannahService.repository.MessageRepository;
import com.savannahinformatics.SavannahService.repository.OrderRepository;
import com.savannahinformatics.SavannahService.request.OrderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    private AuthService authService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public void addOrder(OrderRequest orderRequest) throws Exception {
        try {
            Customer customer = authService.getCurrentUser();
            Order order = new Order();
            order.setAmount(orderRequest.getAmount());
            order.setItem(orderRequest.getItem());
            order.setCustomer(customer);
            order.setCreatedAt(Instant.now());
            order.setUpdatedAt(Instant.now());

            orderRepository.save(order);

            //Save message to messages table
            Message message = new Message();
            message.setMessage("Dear " + customer.getName() + ", your order has been received.");
            message.setRecipient(customer.getPhone());
            message.setStatus("0");
            message.setDescription("INITIATED");
            message.setCreatedAt(Instant.now());
            message.setUpdatedAt(Instant.now());

            messageRepository.save(message);

        }catch (Exception e){
            LOGGER.error("Error occurred adding order " + e);
            throw new Exception("Error occurred " + e);
        }
    }
    @Scheduled(fixedDelay = 3000)
    @Transactional
    public void sendSms() throws Exception {
        try {
            List<Message> pendingSms = messageRepository.findMessageByStatus("0");
            LOGGER.info("{} pending message(s)", pendingSms.size());
            for (Message sms: pendingSms) {
                messageService.sendSms(sms.getId(), sms.getRecipient(), sms.getMessage());
            }

        }catch (Exception e){
            LOGGER.error("Sending notification failed " + e);
            throw new Exception("Error occurred " + e);
        }

    }
}
