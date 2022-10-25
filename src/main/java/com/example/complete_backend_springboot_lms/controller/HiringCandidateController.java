package com.example.complete_backend_springboot_lms.controller;

import com.example.complete_backend_springboot_lms.dto.HiringCandidateDTO;
import com.example.complete_backend_springboot_lms.dto.ResponseDTO;
import com.example.complete_backend_springboot_lms.entity.HiredCandidate;
import com.example.complete_backend_springboot_lms.service.HiredCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hiredCandidateSystem")
public class HiringCandidateController {

    @Autowired
    HiredCandidateService hiredCandidateService;

    @PostMapping(value = "/hiredCandidate")
    public ResponseEntity<ResponseDTO> addHiredCandidate(@Valid @RequestBody HiringCandidateDTO candidateDTO){
        System.out.println(candidateDTO);
        HiringCandidateDTO candidateDTO1 = hiredCandidateService.addHiredCandidateInfo(candidateDTO);
        ResponseDTO responseDTO = new ResponseDTO(candidateDTO1,"Sucessfully added hired CandidateDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/AllhiredCandidate")
    public ResponseEntity<ResponseDTO> getAllHiredCandidate(){
        List<HiredCandidate> hiredCandidates = hiredCandidateService.getAllHiredCandidateInfo();
        ResponseDTO responseDTO = new ResponseDTO(hiredCandidates,"Sucessfully Fetched All hired CandidateDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/hiredCandidateById")
    public ResponseEntity<ResponseDTO> getHiredCandidateByID(@RequestParam int id){
        HiredCandidate candidate = hiredCandidateService.getHiredCandidateInfoById(id);
        ResponseDTO responseDTO = new ResponseDTO(candidate,"Sucessfully Fetched Given Id hired CandidateDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/hiredCandidate")
    public ResponseEntity<ResponseDTO> editHiredCandidateInfo(@RequestBody HiringCandidateDTO hiringCandidateDTO){
        HiredCandidate editHiredCandidateInfo = hiredCandidateService.editHiredCandidateInfo((int) hiringCandidateDTO.getId(),hiringCandidateDTO);
        ResponseDTO responseDTO = new ResponseDTO(editHiredCandidateInfo,"Sucessfully Edited Given Id hiredCandidateDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/hiredCandidate")
    public ResponseEntity<ResponseDTO> deleteHiredCandidateByID(@RequestParam int id){
        hiredCandidateService.deleteHiredCandidateInfo(id);
        ResponseDTO responseDTO = new ResponseDTO(null,"Sucessfully Deleted Given Id hired CandidateDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
