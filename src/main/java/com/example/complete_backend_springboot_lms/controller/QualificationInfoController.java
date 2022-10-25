package com.example.complete_backend_springboot_lms.controller;

import com.example.complete_backend_springboot_lms.dto.QualificationDTO;
import com.example.complete_backend_springboot_lms.dto.ResponseDTO;
import com.example.complete_backend_springboot_lms.entity.QualificationInfo;
import com.example.complete_backend_springboot_lms.service.QualificationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/qualificationInfoSystem")
public class QualificationInfoController {

    @Autowired
    QualificationInfoService qualificationInfoService;

    @PostMapping(value = "/qualificationInfo")
    public ResponseEntity<ResponseDTO> addQualificationInfo(@RequestBody QualificationDTO qualificationDTO){
        QualificationDTO candidateInfo = qualificationInfoService.addQualificationInfo(qualificationDTO);
        ResponseDTO responseDTO = new ResponseDTO(candidateInfo,"Sucessfully added qualificationInfo");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/allQualificationInfo")
    public ResponseEntity<ResponseDTO> getAllOnboardedCandidate(){
        List<QualificationInfo> candidateList = qualificationInfoService.getAllQualificationInfo();
        ResponseDTO responseDTO = new ResponseDTO(candidateList,"Sucessfully Fetched All QualificationInfo");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/qualificationInfoById")
    public ResponseEntity<ResponseDTO> getOnboardedCandidateByID(@RequestParam int id){
        QualificationInfo candidate = qualificationInfoService.getQualificationInfoById(id);
        ResponseDTO responseDTO = new ResponseDTO(candidate,"Sucessfully Fetched Given Id QualificationInfo");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/qualificationInfo")
    public ResponseEntity<ResponseDTO> editQualificationInfo(@RequestBody QualificationDTO qualificationDTO){
        QualificationInfo editQualificationInfo = qualificationInfoService.editQualificationInfo((int) qualificationDTO.getId(),qualificationDTO);
        ResponseDTO responseDTO = new ResponseDTO(editQualificationInfo,"Sucessfully Edited Given Id Candidate Qualification Details");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/qualificationInfo")
    public ResponseEntity<ResponseDTO> deleteOnboardedCandidateByID(@RequestParam int id){
        qualificationInfoService.deleteQualificationInfo(id);
        ResponseDTO responseDTO = new ResponseDTO(null,"Sucessfully Deleted Given Id QualificationInfo");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
