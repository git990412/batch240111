package com.ll.batch240111;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
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
                .tasklet(outHelloWorldTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet outHelloWorldTasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("Hello, World!");
            return RepeatStatus.FINISHED;
        };
    }
}
