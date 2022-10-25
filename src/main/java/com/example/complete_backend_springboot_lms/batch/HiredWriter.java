package com.example.complete_backend_springboot_lms.batch;

import com.example.complete_backend_springboot_lms.entity.HiredCandidate;
import com.example.complete_backend_springboot_lms.repository.HiredRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class HiredWriter implements ItemWriter<HiredCandidate> {

    @Autowired
    private HiredRepository hiredRepository;

    @Override
    public void write(List<? extends HiredCandidate> hiredCandidates) throws Exception {
        hiredRepository.saveAll(hiredCandidates);
        System.out.println("Saved HiredCandidate = "+hiredCandidates);
    }
}
