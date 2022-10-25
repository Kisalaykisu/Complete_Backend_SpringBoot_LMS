package com.example.complete_backend_springboot_lms.config;

import com.example.complete_backend_springboot_lms.entity.HiredCandidate;
import com.example.complete_backend_springboot_lms.entity.OnboardedCandidate;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                   ItemReader<HiredCandidate> itemReader, ItemProcessor<HiredCandidate,HiredCandidate> itemProcessor,
                   ItemWriter<HiredCandidate> itemWriter) {

        Step step = stepBuilderFactory.get("ETL-file-load")
                .<HiredCandidate, HiredCandidate>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory.get("ETL-load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public FlatFileItemReader<HiredCandidate> itemReader(@Value("${input}") Resource resource){
        System.out.println("In itemReader1");
        FlatFileItemReader<HiredCandidate> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setName("Csv-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(linemapper());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<HiredCandidate> linemapper() {
        DefaultLineMapper<HiredCandidate> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"firstName","middleName","lastName","email","mobileNum","hiredCity","hiredDate","degree","hiredLab","attitudeRemark","communicationRemark","knowledgeRemark","onboardingStatus","status","creatorUser","joindate","location","aggrPer","currentPincode","permanentPincode"});

        BeanWrapperFieldSetMapper<HiredCandidate> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(HiredCandidate.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }

    @Bean
    public Job job1(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                    ItemReader<OnboardedCandidate> itemReader, ItemProcessor<OnboardedCandidate,OnboardedCandidate> itemProcessor,
                    ItemWriter<OnboardedCandidate> itemWriter) {

        Step step = stepBuilderFactory.get("ETL-file-load1")
                .<OnboardedCandidate, OnboardedCandidate>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory.get("ETL-load1")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public FlatFileItemReader<OnboardedCandidate> itemReader1(@Value("${input1}") Resource resource){
        System.out.println("In itemReader2");
        FlatFileItemReader<OnboardedCandidate> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setName("Csv-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(linemapper1());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<OnboardedCandidate> linemapper1() {
        DefaultLineMapper<OnboardedCandidate> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"firstName","middleName","lastName","email","mobileNum","hiredCity","parentName","parentMobile","temporaryAddress","parentOccupation","parentAnnualSalary","permanentAddress","status"});

        BeanWrapperFieldSetMapper<OnboardedCandidate> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(OnboardedCandidate.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }
}
