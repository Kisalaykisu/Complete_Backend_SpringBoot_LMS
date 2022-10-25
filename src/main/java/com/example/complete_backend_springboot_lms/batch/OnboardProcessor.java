package com.example.complete_backend_springboot_lms.batch;

import com.example.complete_backend_springboot_lms.entity.HiredCandidate;
import com.example.complete_backend_springboot_lms.entity.OnboardedCandidate;
import com.example.complete_backend_springboot_lms.repository.OnboardedRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Component
public class OnboardProcessor implements ItemProcessor<OnboardedCandidate, OnboardedCandidate> {

    @Autowired
    private OnboardedRepository onboardedRepository;

    @Override
    public OnboardedCandidate process(OnboardedCandidate onboardedCandidate) throws Exception {
        onboardedCandidate.setId(idGenerator());
        onboardedCandidate.setCreatorStamp(LocalDate.now());
        return onboardedCandidate;
    }

    public int idGenerator() {
        Random r = new Random();
        int low = 100;
        int high = 999;
        int result = r.nextInt(high-low) + low;
        Optional<OnboardedCandidate> candidate = onboardedRepository.findById(result);
        if(candidate.isEmpty()){
            System.out.println("ID = "+result);
        }else {
            idGenerator();
        }
        return result;
    }
}
