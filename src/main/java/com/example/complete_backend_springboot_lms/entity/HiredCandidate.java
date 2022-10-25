package com.example.complete_backend_springboot_lms.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class HiredCandidate {

    @Id
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String mobileNum;
    private String hiredCity;
    private String hiredDate;
    private String degree;
    private String hiredLab;
    private String attitudeRemark;
    private String communicationRemark;
    private String knowledgeRemark;
    private String onboardingStatus;
    private String status;
    private String creatorUser;
    private String joindate;
    private String location;
    private double aggrPer;
    private int currentPincode;
    private int permanentPincode;
}
