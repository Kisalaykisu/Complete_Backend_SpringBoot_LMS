package com.example.complete_backend_springboot_lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QualificationDTO {
    private long id;

    @NotBlank(message = "diploma can not be null")
    @Pattern(regexp = "true | false", message = "diploma value invalid")
    private boolean diploma;

    @NotBlank(message = "degree can not be null")
    private String degree;

    @NotBlank(message = "field can not be null")
    private String field;

    @NotBlank(message = "yearOfPassing can not be null")
    private String yearOfPassing;

    @NotBlank(message = "finalPercentage can not be null")
    private String finalPercentage;

    @NotBlank(message = "aggrPercentage can not be null")
    private String aggrPercentage;

    @NotBlank(message = "enggCertificate can not be null")
    private String enggCertificate;

    @NotBlank(message = "finalCertificate can not be null")
    private String finalCertificate;

    @NotBlank(message = "trainingInstitute can not be null")
    private String trainingInstitute;

    @NotBlank(message = "trainingDuration can not be null")
    private String trainingDuration;

    @NotBlank(message = "course can not be null")
    private String course;
}
