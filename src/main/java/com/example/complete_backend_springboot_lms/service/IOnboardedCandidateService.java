package com.example.complete_backend_springboot_lms.service;

import com.example.complete_backend_springboot_lms.dto.OnboardingCandidateDTO;
import com.example.complete_backend_springboot_lms.entity.OnboardedCandidate;
import java.util.List;

public interface IOnboardedCandidateService {
    public OnboardingCandidateDTO addOnboardedCandidateInfo(OnboardingCandidateDTO candidateDTO);
    public List<OnboardedCandidate> getAllOnboardedCandidateInfo();
    public OnboardedCandidate getOnboardedCandidateInfoById(int id);
    public void deleteOnboardedCandidateInfo(int id);
    public OnboardedCandidate editOnboardedCandidateInfo(int id,OnboardingCandidateDTO candidateDTO);
    public int idGenerator();
}
