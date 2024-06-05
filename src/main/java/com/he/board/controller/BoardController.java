package com.he.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.he.board.service.BoardService;

import com.he.board.vo.BoardVO.BoardVO;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.view.RedirectView;

import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/write")
    public String write() {
        return "board/BoardAdd";
    }
    

    @PostMapping(value = "/writedo")
    @ResponseBody
    public RedirectView writedo(@ModelAttribute BoardVO boardVO, Model model, @RequestParam("file") MultipartFile file) throws Exception {
        //jpa엔티티에 multipartfile 타입이 정의되면 안된다. 지원하지 않음. -> dto, vo로 받을 수 없다.
        boardService.addBoard(boardVO, file);
        return new RedirectView("/board/list");
    }

    @GetMapping("/list")
    public String boardList(Model model) {

        List<BoardVO> board = boardService.getList();
        model.addAttribute("board", board);

        return "board/BoardList";
    } 

    @GetMapping("/boarddetail")
    public String boardDetail(@RequestParam(value = "id") String id, Model model) {

        BoardVO vo = boardService.boardDetail(Integer.parseInt(id));
        model.addAttribute("boardVO", vo);

        if(vo.getFileEncryptName() != null && vo.getFileEncryptName() != ""){
            model.addAttribute("filePath", "http://localhost:8080/image/" + vo.getFileEncryptName());
        }else{
            model.addAttribute("filePath", "");
        }
        
        return "board/BoardDetail";
    }

    @GetMapping("/boardremove")
    public RedirectView boardRemove(@RequestParam(value = "id") String id) {

        boardService.boardRemove(id);

        return new RedirectView("/board/list");
    }

    @PostMapping("/updateboard")
    public String postMethodName(@ModelAttribute BoardVO boardVO, Model model) {

        model.addAttribute("vo", boardVO);

        return "board/boardUpdate";
    }

    @PostMapping("/boardupdatedo")
    public RedirectView postMethodName(@ModelAttribute BoardVO boardVO) {

        System.out.println(">>>>>>>>>>>>>> "+boardVO.getBoardID());
        
        boardService.boardUpdate(boardVO);

        return new RedirectView("/board/list");
    }
    
    
    
    

}
