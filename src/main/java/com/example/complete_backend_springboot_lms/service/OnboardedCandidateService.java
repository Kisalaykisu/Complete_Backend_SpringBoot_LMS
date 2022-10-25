package com.example.complete_backend_springboot_lms.service;

import com.example.complete_backend_springboot_lms.controller.BankInfoController;
import com.example.complete_backend_springboot_lms.controller.QualificationInfoController;
import com.example.complete_backend_springboot_lms.dto.OnboardingCandidateDTO;
import com.example.complete_backend_springboot_lms.entity.BankInfo;
import com.example.complete_backend_springboot_lms.entity.HiredCandidate;
import com.example.complete_backend_springboot_lms.entity.OnboardedCandidate;
import com.example.complete_backend_springboot_lms.entity.QualificationInfo;
import com.example.complete_backend_springboot_lms.exception.UserDefinedException;
import com.example.complete_backend_springboot_lms.repository.BankInfoRepository;
import com.example.complete_backend_springboot_lms.repository.OnboardedRepository;
import com.example.complete_backend_springboot_lms.repository.QualificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class OnboardedCandidateService implements IOnboardedCandidateService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OnboardedRepository onboardedRepository;

    @Autowired
    private BankInfoRepository bankInfoRepository;

    @Autowired
    private QualificationRepository qualificationRepository;

    @Autowired
    private BankInfoController bankInfoController;

    @Autowired
    private QualificationInfoController qualificationInfoController;

    @Autowired
    private BankInfoService bankInfoService;

    @Autowired
    private QualificationInfoService qualificationInfoService;

    @Override
    public OnboardingCandidateDTO addOnboardedCandidateInfo(OnboardingCandidateDTO candidateDTO) {
        if(candidateDTO.getMiddleName() != ""){
            String regex = "^[A-Z][A-Za-z0-9\\s]{2,}$";
            boolean match = candidateDTO.getMiddleName().matches(regex);
            if(match == false){
                throw new UserDefinedException("middleName should startWith upperCase & Should have min 3 character");
            }
        }
        candidateDTO.setCreatorStamp(LocalDate.now());
        int id = idGenerator();
        candidateDTO.setId(id);
        bankInfoService.addBankInfo(candidateDTO.getBankInfo());
        qualificationInfoService.addQualificationInfo(candidateDTO.getQualificationInfo());
        OnboardedCandidate candidate = modelMapper.map(candidateDTO,OnboardedCandidate.class);
        onboardedRepository.save(candidate);
        return candidateDTO;
    }

    @Override
    public List<OnboardedCandidate> getAllOnboardedCandidateInfo() {
        return onboardedRepository.findAll();
    }

    @Override
    public OnboardedCandidate getOnboardedCandidateInfoById(int id) {
        return onboardedRepository.findById(id)
                .orElseThrow(()-> new UserDefinedException("Unable to find requested OnBoarded Candidate Information"));
    }

    @Override
    public void deleteOnboardedCandidateInfo(int id) {
        if(id > 0){
            OnboardedCandidate candidate = getOnboardedCandidateInfoById(id);
            bankInfoRepository.deleteById((int) candidate.getBankInfo().getId());
            qualificationRepository.deleteById((int) candidate.getQualificationInfo().getId());
            onboardedRepository.deleteById(id);
        }else {
            throw new UserDefinedException("Requested Delete ID must be positive ! But got ID = "+id);
        }
    }

    @Override
    public OnboardedCandidate editOnboardedCandidateInfo(int id, OnboardingCandidateDTO candidateDTO) {
        if(candidateDTO.getMiddleName() != ""){
            String regex = "^[A-Z][A-Za-z0-9\\s]{2,}$";
            boolean match = candidateDTO.getMiddleName().matches(regex);
            if(match == false){
                throw new UserDefinedException("middleName should startWith upperCase & Should have min 3 character");
            }
        }
        OnboardedCandidate editDetails = null;
        if (id > 0) {
            editDetails = getOnboardedCandidateInfoById(id);
            BankInfo editBankInfo = bankInfoService.editBankInfo((int) candidateDTO.getBankInfo().getId(),candidateDTO.getBankInfo());
            QualificationInfo qualificationInfo = qualificationInfoService.editQualificationInfo((int) candidateDTO.getQualificationInfo().getId(),candidateDTO.getQualificationInfo());
            editDetails.setBankInfo(editBankInfo);
            editDetails.setQualificationInfo(qualificationInfo);
            System.out.println("Edit Details = " + editDetails);
//            deleteOnboardedCandidateInfo(id);
            candidateDTO.setUpdateStamp(LocalDate.now());
            String[] ignoreFields = {"id"};
            BeanUtils.copyProperties(candidateDTO, editDetails, ignoreFields);
            onboardedRepository.save(editDetails);
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
