package com.example.complete_backend_springboot_lms.service;

import com.example.complete_backend_springboot_lms.dto.QualificationDTO;
import com.example.complete_backend_springboot_lms.entity.Admin;
import com.example.complete_backend_springboot_lms.entity.QualificationInfo;
import com.example.complete_backend_springboot_lms.exception.UserDefinedException;
import com.example.complete_backend_springboot_lms.repository.QualificationRepository;
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
public class QualificationInfoService implements IQualificationInfoService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private QualificationRepository qualificationRepository;

    @Override
    public QualificationDTO addQualificationInfo(@Valid @RequestBody QualificationDTO qualificationDTO) {
        int id = idGenerator();
        qualificationDTO.setId(id);
        QualificationInfo qualificationInfo = modelMapper.map(qualificationDTO,QualificationInfo.class);
        qualificationRepository.save(qualificationInfo);
        return qualificationDTO;
    }

    @Override
    public List<QualificationInfo> getAllQualificationInfo() {
        return qualificationRepository.findAll();
    }

    @Override
    public QualificationInfo getQualificationInfoById(int id) {
        return qualificationRepository.findById(id)
                .orElseThrow(()-> new UserDefinedException("Unable to find requested Candidate Qualification Information"));
    }

    @Override
    public void deleteQualificationInfo(int id) {
        if(id > 0){
            qualificationRepository.deleteById(id);
        }else {
            throw new UserDefinedException("Requested Delete ID must be positive ! But got ID = "+id);
        }
    }

    @Override
    public QualificationInfo editQualificationInfo(int id,@Valid QualificationDTO qualificationDTO) {
        QualificationInfo editDetails = null;
        if (id > 0) {
            editDetails = getQualificationInfoById(id);
//            deleteQualificationInfo(id);
            System.out.println("Edit Details = " + editDetails);
            String[] ignoreFields = {"id"};
            BeanUtils.copyProperties(qualificationDTO, editDetails, ignoreFields);
            qualificationRepository.save(editDetails);
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
