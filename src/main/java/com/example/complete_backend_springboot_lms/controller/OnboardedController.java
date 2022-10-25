package com.example.complete_backend_springboot_lms.controller;

import com.example.complete_backend_springboot_lms.dto.OnboardingCandidateDTO;
import com.example.complete_backend_springboot_lms.dto.ResponseDTO;
import com.example.complete_backend_springboot_lms.entity.OnboardedCandidate;
import com.example.complete_backend_springboot_lms.service.OnboardedCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/onboardedCandidateSystem")
public class OnboardedController {

    @Autowired
    OnboardedCandidateService onboardedCandidateService;

    @PostMapping(value = "/onboardedCandidate")
    public ResponseEntity<ResponseDTO> addOnboardedCandidate(@Valid @RequestBody OnboardingCandidateDTO candidateDTO){
        System.out.println("Inside onboardedCandidate");
        OnboardingCandidateDTO candidateInfo = onboardedCandidateService.addOnboardedCandidateInfo(candidateDTO);
        ResponseDTO responseDTO = new ResponseDTO(candidateInfo,"Sucessfully added Onboarded CandidateDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/allOnboardedCandidate")
    public ResponseEntity<ResponseDTO> getAllOnboardedCandidate(){
        List<OnboardedCandidate> candidateList = onboardedCandidateService.getAllOnboardedCandidateInfo();
        ResponseDTO responseDTO = new ResponseDTO(candidateList,"Sucessfully Fetched All Onboarded CandidateDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/OnboardedCandidateById")
    public ResponseEntity<ResponseDTO> getOnboardedCandidateByID(@RequestParam int id){
        OnboardedCandidate candidate = onboardedCandidateService.getOnboardedCandidateInfoById(id);
        ResponseDTO responseDTO = new ResponseDTO(candidate,"Sucessfully Fetched Given Id Onboarded CandidateDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/OnboardedCandidate")
    public ResponseEntity<ResponseDTO> editOnboardedCandidateInfo(@RequestBody OnboardingCandidateDTO candidateDTO){
        OnboardedCandidate editOnboardedCandidateInfo = onboardedCandidateService.editOnboardedCandidateInfo((int) candidateDTO.getId(),candidateDTO);
        ResponseDTO responseDTO = new ResponseDTO(editOnboardedCandidateInfo,"Sucessfully Edited Given Id OnboardedCandidateDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/OnboardedCandidate")
    public ResponseEntity<ResponseDTO> deleteOnboardedCandidateByID(@RequestParam int id){
        onboardedCandidateService.deleteOnboardedCandidateInfo(id);
        ResponseDTO responseDTO = new ResponseDTO(null,"Sucessfully Deleted Given Id OnboardedCandidate Details");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
