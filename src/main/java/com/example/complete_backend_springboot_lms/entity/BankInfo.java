package com.example.complete_backend_springboot_lms.entity;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class BankInfo {

    @Id
    private long id;
    private String panNumber;
    private String aadharNumber;
    private String bankName;
    private String bankAccountNumber;
    private String ifscCode;
    private String passbookPath;
    private String panPath;
    private String aadharPath;
    private LocalDate creatorStamp;
    private LocalDate updateStamp;
}
