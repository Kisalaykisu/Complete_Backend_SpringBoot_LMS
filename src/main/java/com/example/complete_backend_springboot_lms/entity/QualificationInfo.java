package com.example.complete_backend_springboot_lms.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class QualificationInfo {

    @Id
    private long id;
    private boolean diploma;
    private String degree;
    private String field;
    private String yearOfPassing;
    private String finalPercentage;
    private String aggrPercentage;
    private String enggCertificate;
    private String finalCertificate;
    private String trainingInstitute;
    private String trainingDuration;
    private String course;
}
