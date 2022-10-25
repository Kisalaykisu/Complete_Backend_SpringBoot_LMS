package com.example.complete_backend_springboot_lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankInfoDTO {
    private long id;

    @NotBlank(message = "panNumber can not be null")
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "panNumber was invalid")
    private String panNumber;

    @NotBlank(message = "aadharNumber can not be null")
    @Pattern(regexp = "^[0-9]{12}$", message = "aadharNumber was invalid")
    private String aadharNumber;

    @NotBlank(message = "bankName can not be null")
    private String bankName;

    @NotBlank(message = "bankAccountNumber can not be null")
    @Pattern(regexp = "^[0-9]{11}$", message = "bankAccountNumber was invalid")
    private String bankAccountNumber;

    @NotBlank(message = "ifscCode can not be null")
    @Pattern(regexp = "^[A-Z]{4}[0-9]{7}$", message = "ifscCode was invalid")
    private String ifscCode;

    @NotBlank(message = "passbookPath can not be null")
    private String passbookPath;

    @NotBlank(message = "panPath can not be null")
    private String panPath;

    @NotBlank(message = "aadharPath can not be null")
    private String aadharPath;

    private LocalDate creatorStamp;
    private LocalDate updateStamp;
}
