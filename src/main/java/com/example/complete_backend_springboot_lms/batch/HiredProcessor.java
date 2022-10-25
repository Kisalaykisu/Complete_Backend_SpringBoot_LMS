package com.example.complete_backend_springboot_lms.batch;

import com.example.complete_backend_springboot_lms.entity.HiredCandidate;
import com.example.complete_backend_springboot_lms.exception.UserDefinedException;
import com.example.complete_backend_springboot_lms.repository.HiredRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
public class HiredProcessor implements ItemProcessor<HiredCandidate, HiredCandidate> {

    @Autowired
    private HiredRepository hiredRepository;

    @Override
    public HiredCandidate process(HiredCandidate hiredCandidate) throws Exception {

        String regex = "^[A-Z][A-Za-z0-9\\s]{2,}$";
        if(hiredCandidate.getFirstName().matches(regex) == false){
            throw new UserDefinedException("FirstName = " +hiredCandidate.getFirstName()+ " was invalid. Please Enter Valid Name!");
        }
        if(hiredCandidate.getLastName().matches(regex) == false){
            throw new UserDefinedException("LastName = " +hiredCandidate.getLastName()+ " was invalid. Please Enter Valid Name!");
        }
        System.out.println("Hired Candidate on Processing..."+ hiredCandidate);
        hiredCandidate.setId(idGenerator());
        return hiredCandidate;
    }

    public int idGenerator() {
        Random r = new Random();
        int low = 100;
        int high = 999;
        int result = r.nextInt(high-low) + low;
        Optional<HiredCandidate> candidate = hiredRepository.findById(result);
        if(candidate.isEmpty()){
            System.out.println("ID = "+result);
        }else {
            idGenerator();
        }
        return result;
    }
}
