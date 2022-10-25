package com.example.complete_backend_springboot_lms.service;

import com.example.complete_backend_springboot_lms.dto.BankInfoDTO;
import com.example.complete_backend_springboot_lms.entity.BankInfo;

import java.util.List;

public interface IBankInfoService {
    public BankInfoDTO addBankInfo(BankInfoDTO bankInfoDTO);
    public List<BankInfo> getAllBankInfo();
    public BankInfo getBankInfoById(int id);
    public void deleteBankInfo(int id);
    public BankInfo editBankInfo(int id,BankInfoDTO bankInfoDTO);
    public int idGenerator();
}
