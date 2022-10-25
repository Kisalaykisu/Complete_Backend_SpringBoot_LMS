package com.example.complete_backend_springboot_lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HiringCandidateDTO {
    private long id;

    @NotEmpty(message = "firstName can not be null")
    @Pattern(regexp = "^[A-Z][A-Za-z0-9\\s]{2,}$" , message = "firstName should startWith upperCase & Should have min 3 character")
    private String firstName;

    private String middleName;

    @NotEmpty(message = "firstName can not be null")
    @Pattern(regexp = "^[A-Z][A-Za-z0-9\\s]{2,}$" , message = "firstName should startWith upperCase & Should have min 3 character")
    private String lastName;

    @Pattern(regexp = "^[a-z0-9]{2,}[@][a-z]{5}[.][a-z]{3}$", message = "Email was Invalid")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "MoblieNumber Should have 10 digit")
    private String mobileNum;

    @NotBlank(message = "hiredCity can not be null")
    private String hiredCity;

    @NotBlank(message = "hiredDate can not be null")
    private String hiredDate;

    @NotBlank(message = "degree can not be null")
    private String degree;

    @NotBlank(message = "hiredLab can not be null")
    private String hiredLab;

    @NotBlank(message = "attitudeRemark can not be null")
    private String attitudeRemark;

    @NotBlank(message = "communicationRemark can not be null")
    private String communicationRemark;

    @NotBlank(message = "knowledgeRemark can not be null")
    private String knowledgeRemark;

    @NotBlank(message = "onboardingStatus can not be null")
    private String onboardingStatus;

    @NotBlank(message = "status can not be null")
    private String status;
    
    private String creatorUser;

    @NotBlank(message = "joindate can not be null")
    private String joindate;

    @NotBlank(message = "location can not be null")
    private String location;

    @NotNull(message = "aggrPer can not be null")
    private double aggrPer;

    @NotNull(message = "currentPincode can not be null")
    private int currentPincode;

    @NotNull(message = "permanentPincode can not be null")
    private int permanentPincode;
}
