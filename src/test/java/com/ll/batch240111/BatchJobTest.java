package com.ll.batch240111;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.Assert;

@SpringBatchTest
@SpringBootTest
public class BatchJobTest {
    @Autowired
    JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    Job job;

    @Test
    public void test(){
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("outHelloWorld");
        Assert.hasText("COMPLETED", jobExecution.getExitStatus().getExitCode());
    }

    @Test
    public void test2() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(new JobParameters());
        Assert.hasText("COMPLETED", jobExecution.getExitStatus().getExitCode());
    }
}
