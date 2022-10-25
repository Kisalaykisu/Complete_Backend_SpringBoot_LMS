package com.example.complete_backend_springboot_lms.service;

import com.example.complete_backend_springboot_lms.dto.HiringCandidateDTO;
import com.example.complete_backend_springboot_lms.entity.BankInfo;
import com.example.complete_backend_springboot_lms.entity.HiredCandidate;
import com.example.complete_backend_springboot_lms.exception.UserDefinedException;
import com.example.complete_backend_springboot_lms.repository.HiredRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class HiredCandidateService implements IHiredCandidateService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailService emailService;

    @Autowired
    private HiredRepository hiredRepository;

    @Override
    public HiringCandidateDTO addHiredCandidateInfo(HiringCandidateDTO hiringCandidateDTO) {
        if(hiringCandidateDTO.getMiddleName() != ""){
            String regex = "^[A-Z][A-Za-z0-9\\s]{2,}$";
            boolean match = hiringCandidateDTO.getMiddleName().matches(regex);
            if(match == false){
                throw new UserDefinedException("middleName should startWith upperCase & Should have min 3 character");
            }
        }
        int id = idGenerator();
        hiringCandidateDTO.setId(id);
        hiringCandidateDTO.setCreatorUser("ADMIN");
        HiredCandidate candidate = modelMapper.map(hiringCandidateDTO,HiredCandidate.class);
        hiredRepository.save(candidate);
        System.out.println(hiringCandidateDTO);
        String subject = "Congratulations....";
        String text = "Hi "+hiringCandidateDTO.getFirstName()+" "+hiringCandidateDTO.getLastName() + "\nYou have Selected !! \n" + "Immediatly should join March 1\n"+
                       "Our Team will reach you soon";
        emailService.sendEmail(hiringCandidateDTO.getEmail(),subject,text);
        return hiringCandidateDTO;
    }

    @Override
    public List<HiredCandidate> getAllHiredCandidateInfo() {
        return hiredRepository.findAll();
    }

    @Override
    public HiredCandidate getHiredCandidateInfoById(int id) {
        return hiredRepository.findById(id)
                .orElseThrow(()-> new UserDefinedException("Unable to find requested HiredCandidate Information"));
    }

    @Override
    public void deleteHiredCandidateInfo(int id) {
        if(id > 0){
            hiredRepository.deleteById(id);
        }else {
            throw new UserDefinedException("Requested Delete ID must be positive ! But got ID = "+id);
        }
    }

    @Override
    public HiredCandidate editHiredCandidateInfo(int id, HiringCandidateDTO hiringCandidateDTO) {
        if(hiringCandidateDTO.getMiddleName() != ""){
            String regex = "^[A-Z][A-Za-z0-9\\s]{2,}$";
            boolean match = hiringCandidateDTO.getMiddleName().matches(regex);
            if(match == false){
                throw new UserDefinedException("middleName should startWith upperCase & Should have min 3 character");
            }
        }
        HiredCandidate editDetails = null;
        if (id > 0) {
            editDetails = getHiredCandidateInfoById(id);
            deleteHiredCandidateInfo(id);
            System.out.println("Edit Details = " + editDetails);
            String[] ignoreFields = {"id"};
            BeanUtils.copyProperties(hiringCandidateDTO, editDetails, ignoreFields);
            hiredRepository.save(editDetails);
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
}
