package com.he.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.he.board.vo.BoardVO.BoardVO;
import java.util.List;
import java.util.Optional;


public interface BoardRepository extends JpaRepository<BoardVO, Integer>{

    List<BoardVO> findByIsDelete(String isDelete);

    Optional<BoardVO> findByIsDeleteAndBoardID(String string, Integer id);
    
}
