package com.example.springbatch.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {

    private int boardNo;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;
    private LocalDateTime updDate;


}
