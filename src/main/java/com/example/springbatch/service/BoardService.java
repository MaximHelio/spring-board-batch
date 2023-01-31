package com.example.springbatch.service;

import com.example.springbatch.domain.Board;

import java.util.List;

public interface BoardService {

    // 게시글 목록
    public List<Board> list() throws Exception;

    // 게시글 쓰기
    public int insert(Board board) throws Exception;

    // 게시글 읽기
    public Board read(int boardNo) throws Exception;

    // 게시글 수정
    public int update(Board board) throws Exception;

    // 게시글 삭제
    public int delete(int boardNo) throws Exception;

}