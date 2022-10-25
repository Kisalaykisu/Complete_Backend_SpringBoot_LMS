package com.example.complete_backend_springboot_lms.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    private String messageId;
    private String to;
    private String subject;
    private String body;
    private Date date;
}
