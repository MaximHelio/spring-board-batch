package com.example.springbatch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class BatchScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private BatchConfig batchConfig;

    // 어떤 스케줄로 실행할지 ? -> cron 표현식 이용
    // 초 분 시 일 월 요일 년: 매 2분마다
    @Scheduled(cron="0 0/2 * * * *")
    public void runJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        // Job Parameter : Job 인스턴스를 고유하게 구별해줄 수 있는 객체
        // Job Parameter를 생성할 때, 고유한 값을 parameter 변수로 지정하여 생성한다.
        // - 파라미터 예시 : UUID, ms 단위의 시간, 날짜, ...
        Map<String, JobParameter> confMap = new HashMap<>();
        confMap.put("time", new JobParameter(System.currentTimeMillis()));

        // Map 객체를 인자로 해서 JobParameter 여러 개를 관리하는 JobParameters 객체 생성
        JobParameters jobParameters = new JobParameters( confMap );

        jobLauncher.run(batchConfig.job(), jobParameters);

//        try {
            // jobLauncher.run( 일괄작업할 Job, jobParameters ) :
            // 지정한 Job을 실행 - 일괄 작업 실행

//            jobLauncher.run(batchConfig.job(), jobParameters);
//        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
//                 | JobParametersInvalidException e) {
//            e.printStackTrace();
//        }
    }

}
