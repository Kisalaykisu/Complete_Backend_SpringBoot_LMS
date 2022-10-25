package com.example.complete_backend_springboot_lms.service;

import com.example.complete_backend_springboot_lms.dto.BankInfoDTO;
import com.example.complete_backend_springboot_lms.dto.QualificationDTO;
import com.example.complete_backend_springboot_lms.entity.BankInfo;
import com.example.complete_backend_springboot_lms.entity.QualificationInfo;

import java.util.List;

public interface IQualificationInfoService {
    public QualificationDTO addQualificationInfo(QualificationDTO qualificationDTO);
    public List<QualificationInfo> getAllQualificationInfo();
    public QualificationInfo getQualificationInfoById(int id);
    public void deleteQualificationInfo(int id);
    public QualificationInfo editQualificationInfo(int id,QualificationDTO qualificationDTO);
    public int idGenerator();
}
