package com.example.springbatch.config;

import com.example.springbatch.domain.Board;
import com.example.springbatch.service.BoardService;
import org.quartz.JobBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration // 설정 파일의 용도로 스프링 빈에 등록됨
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BoardService boardService;

    @Bean
    public Job job(){
        Job job = jobBuilderFactory.get("job")
                .start( step() )
                .build();
        return job;
    }

    @Bean
    public Step step() {
        Step step = stepBuilderFactory.get("step")
                    .tasklet((contribution, chunkContext) -> {
                        // tasklet (기능) 정의
                        // - 일괄처리하려는 작업을 진행
                        // "오늘 날짜의 제목으로 board 테이블에 게시글 생성"
                        Date date = new Date();
                        String title = date.toString();
                        String writer = "작성자";
                        String content = "내용";

                        Board board = new Board();
                        board.setTitle(title);
                        board.setWriter(writer);
                        board.setContent(content);

                        boardService.insert(board);

                        return RepeatStatus.FINISHED;
                    })
                    .build();
        return step;
    }
}
