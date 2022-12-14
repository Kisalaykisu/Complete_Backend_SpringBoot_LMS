package com.example.complete_backend_springboot_lms.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoadController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @Autowired
    Job job1;

    @GetMapping(value = "/loadHiredCandidate")
    public BatchStatus loadHiredCandidate() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job,parameters);
        System.out.println("JobExecution = "+jobExecution.getStatus());
        return jobExecution.getStatus();
    }

    @GetMapping(value = "/loadOnboardedCandidate")
    public BatchStatus load1OnboardedCandidate() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter> maps1 = new HashMap<>();
        maps1.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps1);
        JobExecution jobExecution = jobLauncher.run(job1,parameters);
        System.out.println("JobExecution = "+jobExecution.getStatus());
        return jobExecution.getStatus();
    }
}
