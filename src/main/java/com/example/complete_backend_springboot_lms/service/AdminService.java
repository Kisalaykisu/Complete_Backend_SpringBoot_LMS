package com.example.complete_backend_springboot_lms.service;

import com.example.complete_backend_springboot_lms.config.JwtRequestFilter;
import com.example.complete_backend_springboot_lms.config.RabbitMqConfig;
import com.example.complete_backend_springboot_lms.dto.AdminDTO;
import com.example.complete_backend_springboot_lms.dto.Email;
import com.example.complete_backend_springboot_lms.entity.Admin;
import com.example.complete_backend_springboot_lms.exception.UserDefinedException;
import com.example.complete_backend_springboot_lms.repository.AdminRepository;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class AdminService implements IAdminService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    public static int OTP = 0000;
    public static Admin passwordChangeAdmin = null;
    public static Admin addNewAdmin = null;
    public static String url = "http://localhost:8080/activate";
    String content="<a href='"+url+"'>"+"Click Me to activate Your Account"+"</a>";

    @Override
    public AdminDTO addAdmin(AdminDTO adminDTO) {
        adminDTO.setCreatorStamp(LocalDate.now());
        int id = idGenerator();
        adminDTO.setId(id);
        adminDTO.setPassword(bcryptEncoder.encode(adminDTO.getPassword()));
        Admin addAdmin = modelMapper.map(adminDTO, Admin.class);
        addNewAdmin = addAdmin;
        String subject = "Process on pending....";
        String text = "Click the below link to activate Your account\n" + "\nAdminName : "+addAdmin.getFirstName() +
                "\nPassword : "+adminDTO.getPassword() +"\n"+ content;
        Email email = new Email(UUID.randomUUID().toString(),adminDTO.getEmailId(),subject,text,new Date());
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE,RabbitMqConfig.ROUTINGKEY1,email);
        System.out.println("Sucessfully Message Send to Queue");
        return adminDTO;
    }


    public ModelAndView activateAccount(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        adminRepository.save(addNewAdmin);
        addNewAdmin = null;
        return modelAndView;
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminById(int id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new UserDefinedException("Unable to find requested admin details"));
    }

    @Override
    public void deleteAdmin(int id) {
        if(id > 0){
            adminRepository.deleteById(id);
        }else {
            throw new UserDefinedException("deleteId must be positive only! But given ID = "+id);
        }

    }

    @Override
    public Admin editAdmin(int id, AdminDTO adminDTO) {
        Admin editDetails = null;
        if (id > 0) {
            editDetails = getAdminById(id);
            deleteAdmin(id);
            String[] ignoreFields = {"id", "creatorStamp", "updatedStamp", "password"};
            editDetails.setUpdatedStamp(LocalDate.now());
            BeanUtils.copyProperties(adminDTO, editDetails, ignoreFields);
            adminRepository.save(editDetails);
        }
        return editDetails;
    }

    @Override
    public int idGenerator() {
        Random r = new Random();
        int low = 100;
        int high = 999;
        int result = r.nextInt(high-low) + low;
        System.out.println("ID = "+result);
        return result;
    }

    @Override
    public Admin forgotPassword(String emailId) {
        System.out.println(emailId);
        Admin getByEmailId = adminRepository.findByEmailId(emailId);
        System.out.println("Email Id = "+getByEmailId);
        passwordChangeAdmin = getByEmailId;
        if(getByEmailId != null){
            Random r = new Random();
            int low = 1000;
            int high = 9999;
            int result = r.nextInt(high-low) + low;
            System.out.println("OTP = "+result);
            OTP = result;
            String subject = "Email verification for Password change...";
            String text = "\n OTP = "+result;
            Email email = new Email(UUID.randomUUID().toString(),emailId,subject,text,new Date());
            rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE,RabbitMqConfig.ROUTINGKEY2,email);
            System.out.println("Sucessfully OTP Message Send to Queue");
            return getByEmailId;
        }else
            throw new UserDefinedException("Unable to find Admin for given EmailId");
    }

    @Override
    public void otpVerification(int otp) {
        if(OTP == otp){
            System.out.println("OTP sucessfully Verified!!");
        }else {
            throw new UserDefinedException("OTP invalid! Please Enter valid otp Number");
        }
    }

    @Override
    public String resetPassword(String newPassword) {
        passwordChangeAdmin.setPassword(bcryptEncoder.encode(newPassword));
        adminRepository.save(passwordChangeAdmin);
        String subject = "Sucessful process....";
        String text = "Sucessfully your password changed...\n" + "AdminName : "+passwordChangeAdmin.getFirstName() +
                "\nPassword : "+passwordChangeAdmin.getPassword() ;
        emailService.sendEmail(passwordChangeAdmin.getEmailId(),subject,text);
        return newPassword;
    }
}
