package com.example.complete_backend_springboot_lms.service;

import com.example.complete_backend_springboot_lms.dto.Email;

public interface IMessageListener {
    public void sendMessage(Email email);
    public void sendMessageWithLink(Email email);
}
