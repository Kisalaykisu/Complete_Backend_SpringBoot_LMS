package com.example.complete_backend_springboot_lms.entity;

import com.example.complete_backend_springboot_lms.dto.BankInfoDTO;
import com.example.complete_backend_springboot_lms.dto.QualificationDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class OnboardedCandidate {

    @Id
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String mobileNum;
    private String hiredCity;
    private String parentName;
    private String parentMobile;
    private String temporaryAddress;
    private String parentOccupation;
    private String parentAnnualSalary;
    private String permanentAddress;
    private String profileImage;
    private String status;
    private LocalDate creatorStamp;
    private LocalDate updateStamp;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private BankInfo bankInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private QualificationInfo qualificationInfo;
}
