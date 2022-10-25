package com.example.complete_backend_springboot_lms.controller;

import com.example.complete_backend_springboot_lms.config.JwtTokenUtil;
import com.example.complete_backend_springboot_lms.config.JwtUserDetailsService;
import com.example.complete_backend_springboot_lms.dto.AdminDTO;
import com.example.complete_backend_springboot_lms.dto.JwtRequest;
import com.example.complete_backend_springboot_lms.dto.ResponseDTO;
import com.example.complete_backend_springboot_lms.entity.Admin;
import com.example.complete_backend_springboot_lms.service.AdminService;
import com.example.complete_backend_springboot_lms.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    EmailService emailService;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * Ability to authenticate User credentials, if verified generate a JWT token.
     * @param authenticationRequest
     * @return token
     */

    @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> authenticateAdmin(@RequestBody JwtRequest authenticationRequest){
        userDetailsService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        ResponseDTO responseDTO = new ResponseDTO(token,"Sucessfully Verified AdminDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to add a new admin Details
     * @param adminDTO
     * @return addAdmin
     */

    @PostMapping(value = "/admin")
    public ResponseEntity<ResponseDTO> addAdmin(@RequestBody AdminDTO adminDTO){
        AdminDTO addAdmin = adminService.addAdmin(adminDTO);
        ResponseDTO responseDTO = new ResponseDTO(addAdmin,"Sucessfully Send Activation link to the Given EmailId");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to retrive all Admin Details from database.
     * @return allAdmin
     */

    @GetMapping(value = "/AllAdmin")
    public ResponseEntity<ResponseDTO> getAllAdmin(){
        List<Admin> allAdmin = adminService.getAllAdmin();
        ResponseDTO responseDTO = new ResponseDTO(allAdmin,"Sucessfully Fetched All AdminDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to get adminDetails by using Id.
     * @param id
     * @return getAdminById
     */

    @GetMapping(value = "/adminById")
    public ResponseEntity<ResponseDTO> getAdminByID(@RequestParam int id){
        Admin getAdminById = adminService.getAdminById(id);
        ResponseDTO responseDTO = new ResponseDTO(getAdminById,"Sucessfully Fetched Given Id AdminDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to edit admin Details in databse.
     * @param adminDTO
     * @return editAdmin
     */

    @PutMapping(value = "/admin")
    public ResponseEntity<ResponseDTO> editAdmin(@RequestBody  AdminDTO adminDTO){
        Admin editAdmin = adminService.editAdmin((int) adminDTO.getId(),adminDTO);
        ResponseDTO responseDTO = new ResponseDTO(editAdmin,"Sucessfully Edited Given Id AdminDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to delete adminDetails by Using ID.
     * @param id
     */

    @DeleteMapping(value = "/admin")
    public ResponseEntity<ResponseDTO> deleteAdminByID(@RequestParam int id){
        adminService.deleteAdmin(id);
        ResponseDTO responseDTO = new ResponseDTO(null,"Sucessfully Deleted Given Id AdminDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to change password, verify given E-mail id
     * If email verified, send an OTP to given EmailId.
     * @param adminDTO
     */

    @PostMapping(value = "/forgotPassword")
    public ResponseEntity<ResponseDTO> forgotPassword(@RequestBody AdminDTO adminDTO){
        Admin admin = adminService.forgotPassword(adminDTO.getEmailId());
        ResponseDTO responseDTO = new ResponseDTO(admin,"Sucessfully send OTP to given EmailId");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to verify the otp, If verified update newPassword.
     * @param adminDTO
     */

    @PostMapping(value = "/otp")
    public ResponseEntity<ResponseDTO> otpVerification(@RequestBody AdminDTO adminDTO){
        adminService.otpVerification(adminDTO.getOtp());
        ResponseDTO responseDTO = new ResponseDTO(null,"OTP verified Sucessfully.");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/resetPassword")
    public ResponseEntity<ResponseDTO> passwordChange(@RequestBody AdminDTO adminDTO){
        String  newPassword = adminService.resetPassword(adminDTO.getNewPassword());
        ResponseDTO responseDTO = new ResponseDTO("Updated password = "+newPassword,"New Password Sucessfully Verified.");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/activate",method = RequestMethod.GET)
    public ModelAndView activateAccount() {
        ModelAndView modelAndView = adminService.activateAccount();
        return modelAndView;
    }

    @PostMapping(value = "/deleteEmail")
    public ResponseEntity<ResponseDTO> deleteEmail(){
        String pop3Host = "pop.gmail.com";
        String mailStoreType = "pop3s";
        final String userName = "kisalaykisu@gmail.com";
        final String password = "fhqtiskhkaptngkf";

        emailService.deleteEmail(pop3Host, mailStoreType, userName, password);
        ResponseDTO responseDTO = new ResponseDTO("Sucessfully Deleted", "ThankYou...");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
