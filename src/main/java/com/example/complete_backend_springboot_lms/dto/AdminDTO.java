package com.example.complete_backend_springboot_lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String mobile;
    private String emailId;
    private String profilePath;
    private boolean status;
    private String password;
    private LocalDate creatorStamp;
    private LocalDate updatedStamp;
    private int otp;
    private String newPassword;
}
