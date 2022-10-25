package com.example.complete_backend_springboot_lms.service;

import com.example.complete_backend_springboot_lms.config.RabbitMqConfig;
import com.example.complete_backend_springboot_lms.dto.Email;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener implements IMessageListener {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = RabbitMqConfig.QUEUE1)
    @Override
    public void sendMessage(Email email) {
        System.out.println("Queue 1");
        System.out.println("Email = "+email);
        emailService.sendLink(email.getTo(), email.getSubject(),email.getBody());
        System.out.println("Message Received Sucessfully");
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE2)
    @Override
    public void sendMessageWithLink(Email email) {
        System.out.println("Queue 2");
        System.out.println("Email with Link = "+email);
        emailService.sendEmail(email.getTo(), email.getSubject(),email.getBody());
        System.out.println("Message Received Sucessfully");
    }
}
