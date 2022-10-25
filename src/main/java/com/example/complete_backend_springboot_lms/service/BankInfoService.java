package com.example.complete_backend_springboot_lms.service;

import com.example.complete_backend_springboot_lms.dto.BankInfoDTO;
import com.example.complete_backend_springboot_lms.entity.Admin;
import com.example.complete_backend_springboot_lms.entity.BankInfo;
import com.example.complete_backend_springboot_lms.exception.UserDefinedException;
import com.example.complete_backend_springboot_lms.repository.BankInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class BankInfoService implements IBankInfoService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BankInfoRepository bankInfoRepository;

    @Override
    public BankInfoDTO addBankInfo(@Valid @RequestBody BankInfoDTO bankInfoDTO) {
        int id = idGenerator();
        bankInfoDTO.setId(id);
        bankInfoDTO.setCreatorStamp(LocalDate.now());
        BankInfo bankInfo = modelMapper.map(bankInfoDTO,BankInfo.class);
        bankInfoRepository.save(bankInfo);
        return bankInfoDTO;
    }

    @Override
    public List<BankInfo> getAllBankInfo() {
        return bankInfoRepository.findAll();
    }

    @Override
    public BankInfo getBankInfoById(int id) {
        return bankInfoRepository.findById(id)
                .orElseThrow(()-> new UserDefinedException("Unable to find requested Bank Information"));
    }

    @Override
    public void deleteBankInfo(int id) {
        if(id > 0) {
            bankInfoRepository.deleteById(id);
        }else {
            throw new UserDefinedException("Requested Delete ID must be positive ! But got ID = "+id);
        }
    }

    @Override
    public BankInfo editBankInfo(int id,@Valid BankInfoDTO bankInfoDTO) {
        BankInfo editDetails = null;
        if (id > 0) {
            editDetails = getBankInfoById(id);
            System.out.println("Edit Details = " + editDetails);
            String[] ignoreFields = {"id", "creatorStamp", "updatedStamp"};
            editDetails.setUpdateStamp(LocalDate.now());
            BeanUtils.copyProperties(bankInfoDTO, editDetails, ignoreFields);
            bankInfoRepository.save(editDetails);
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
