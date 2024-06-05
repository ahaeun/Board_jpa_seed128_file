package com.he.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.he.board.vo.BoardVO.BoardVO;

public interface BoardService {

    void addBoard(BoardVO boardVO, MultipartFile file) throws Exception;

    List<BoardVO> getList();

    BoardVO boardDetail(Integer id);

    void boardRemove(String id);

    void boardUpdate(BoardVO boardVO);
    
}
