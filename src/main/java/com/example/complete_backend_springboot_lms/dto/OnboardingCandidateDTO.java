package com.example.complete_backend_springboot_lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingCandidateDTO {
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

    @NotBlank(message = "parentName can not be null")
    @Pattern(regexp = "^[A-Z][A-Za-z0-9\\s]{2,}$" , message = "firstName should startWith upperCase & Should have min 3 character")
    private String parentName;

    @NotBlank(message = "parentMobile can not be null")
    @Pattern(regexp = "^[0-9]{10}$", message = "Parent MoblieNumber Should have 10 digit")
    private String parentMobile;

    @NotBlank(message = "temporaryAddress can not be null")
    private String temporaryAddress;

    @NotBlank(message = "parentOccupation can not be null")
    private String parentOccupation;

    @NotBlank(message = "parentAnnualSalary can not be null")
    private String parentAnnualSalary;

    @NotBlank(message = "permanentAddress can not be null")
    private String permanentAddress;

    @NotBlank(message = "profileImage can not be null")
    private String profileImage;

    @NotBlank(message = "status can not be null")
    private String status;

    private LocalDate creatorStamp;
    private LocalDate updateStamp;

    @NotNull(message = "bankInfo can not be null")
    private BankInfoDTO bankInfo;

    @NotNull(message = "qualificationInfo can not be null")
    private QualificationDTO qualificationInfo;
}
