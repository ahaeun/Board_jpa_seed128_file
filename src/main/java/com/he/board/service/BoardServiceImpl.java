package com.he.board.service;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.he.board.config.Seed128;
import com.he.board.repository.BoardRepository;
import com.he.board.utils.Path;
import com.he.board.vo.BoardVO.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private Seed128 seed;

    @Override
    public void addBoard(BoardVO boardVO, MultipartFile file) throws Exception {

        seed.seedKey("password12345678");

        // 파일 이름과 확장자 분리
        String[] subString = file.getOriginalFilename().split("\\.");

        String fileName = subString[0];
        String fileExtension = subString[1];

        // 파일 이름 암호화
        String enc = seed.encrypt(fileName);

        // 파일 이동
        String path = Path.BoardPath;
        File saveFile = new File(path, enc + "." + fileExtension);
        file.transferTo(saveFile);

        // vo 세팅
        boardVO = new BoardVO(boardVO, file, enc, fileExtension);
        boardVO.setFileEncryptName(saveFile.getName());
        
        boardRepository.save(boardVO);

    }

    @Override
    public List<BoardVO> getList() {
        
        return boardRepository.findByIsDelete("N");

    }

    @Override
    public BoardVO boardDetail(Integer id) {
        Optional<BoardVO> board = boardRepository.findByIsDeleteAndBoardID("N", id);
        return board.get();
    }

    @Override
    public void boardRemove(String id) {
        
        Optional<BoardVO> board = boardRepository.findById(Integer.parseInt(id));
        BoardVO vo = board.get();

        vo.setIsDelete("Y");

        boardRepository.save(vo);
    }

    @Override
    public void boardUpdate(BoardVO boardVO) {
        
        boardRepository.save(boardVO);

    }
    
}
