package com.example.springbatch.service;

import com.example.springbatch.domain.Board;
import com.example.springbatch.mapper.BoardMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    // 의존성 주입
    @Autowired
    private BoardMapper mapper;

    @Override
    public List<Board> list() throws Exception {
        return mapper.list();
    }

    @Override
    public int insert(Board board) throws Exception {
        int result = mapper.insert(board);

        if( result > 0 ){
            log.info("데이터를 추가했습니다...");
        }

        return result;
    }

    @Override
    public Board read(int boardNo) throws Exception {
        return mapper.read(boardNo);
    }

    @Override
    public int update(Board board) throws Exception {
        return mapper.update(board);
    }

    @Override
    public int delete(int boardNo) throws Exception {

        // 게시글 삭제
        return mapper.delete(boardNo);
    }




}

