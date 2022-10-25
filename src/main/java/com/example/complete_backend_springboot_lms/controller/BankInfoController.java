package com.example.complete_backend_springboot_lms.controller;

import com.example.complete_backend_springboot_lms.dto.BankInfoDTO;
import com.example.complete_backend_springboot_lms.dto.ResponseDTO;
import com.example.complete_backend_springboot_lms.entity.BankInfo;
import com.example.complete_backend_springboot_lms.service.BankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/bankInfoSystem")
public class BankInfoController {

    @Autowired
    BankInfoService bankInfoService;

    @PostMapping(value = "/bankInfo")
    public ResponseEntity<ResponseDTO> addCandidateBankInfo(@RequestBody BankInfoDTO bankInfoDTO){
        BankInfoDTO addBankInfo = bankInfoService.addBankInfo(bankInfoDTO);
        ResponseDTO responseDTO = new ResponseDTO(addBankInfo,"Sucessfully added candidate BankDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/AllBankInfo")
    public ResponseEntity<ResponseDTO> getAllCandidateBankInfo(){
        List<BankInfo> allBankInfo = bankInfoService.getAllBankInfo();
        ResponseDTO responseDTO = new ResponseDTO(allBankInfo,"Sucessfully Fetched All candidate BankDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/BankInfoById")
    public ResponseEntity<ResponseDTO> getCandidateBankInfoByID(@RequestParam int id){
        BankInfo bankInfo = bankInfoService.getBankInfoById(id);
        ResponseDTO responseDTO = new ResponseDTO(bankInfo,"Sucessfully Fetched Given Id candidate BankDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/BankInfo")
    public ResponseEntity<ResponseDTO> editCandidateBankInfo(@RequestBody BankInfoDTO bankInfoDTO){
        BankInfo editBankInfo = bankInfoService.editBankInfo((int) bankInfoDTO.getId(),bankInfoDTO);
        ResponseDTO responseDTO = new ResponseDTO(editBankInfo,"Sucessfully Edited Given Id CandidateBankInfoDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/BankInfo")
    public ResponseEntity<ResponseDTO> deleteCandidateBankInfoByID(@RequestParam int id){
        bankInfoService.deleteBankInfo(id);
        ResponseDTO responseDTO = new ResponseDTO(null,"Sucessfully Deleted Given Id candidate BankDetails");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
