package com.example.complete_backend_springboot_lms.batch;

import com.example.complete_backend_springboot_lms.entity.OnboardedCandidate;
import com.example.complete_backend_springboot_lms.repository.OnboardedRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OnboardWriter implements ItemWriter<OnboardedCandidate> {

    @Autowired
    private OnboardedRepository onboardedRepository;

    @Override
    public void write(List<? extends OnboardedCandidate> onboardedCandidates) throws Exception {
        onboardedRepository.saveAll(onboardedCandidates);
        System.out.println("Sucessfully saved onBoardCandidate = "+onboardedCandidates);
    }
}
