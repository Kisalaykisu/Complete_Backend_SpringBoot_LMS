package com.example.complete_backend_springboot_lms.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Admin {

    @Id
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
}
