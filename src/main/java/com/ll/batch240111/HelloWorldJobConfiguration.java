package com.ll.batch240111;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class HelloWorldJobConfiguration {
    @Bean
    public Job taskletJob(JobRepository jobRepository, Step outHelloWorld) {
        return new JobBuilder("taskletJob", jobRepository)
                .start(outHelloWorld)
                .build();
    }

    @Bean
    public Step outHelloWorld(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("outHelloWorld", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Hello, World!");
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}
