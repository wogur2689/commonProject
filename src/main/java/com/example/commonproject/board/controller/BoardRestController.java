package com.example.commonproject.board.controller;

import com.example.commonproject.board.dto.BoardRequestDTO;
import com.example.commonproject.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardRestController {

    private final BoardService boardService;

    /**
     * 게시글 생성
     */
    @PostMapping("/create")
    public ModelAndView boardCreate(@RequestBody @Valid BoardRequestDTO boardRequestDTO, ModelAndView mav) {
        mav.addObject("board", boardService.boardSave(boardRequestDTO));
        mav.setViewName("jsonView");
        return mav;
    }

    /**
     * 게시글 수정
     */
    @PostMapping("/update")
    public ModelAndView boardUpdate(@RequestBody @Valid BoardRequestDTO boardRequestDTO, ModelAndView mav) {
        mav.addObject("board", boardService.boardUpdate(boardRequestDTO));
        mav.setViewName("jsonView");
        return mav;
    }

    /**
     * 게시글 삭제
     */
    @PostMapping("/delete")
    public ModelAndView boardDelete(@RequestBody @Valid BoardRequestDTO boardRequestDTO, ModelAndView mav) {
        boardService.boardDelete(boardRequestDTO);
        mav.setViewName("jsonView");
        return mav;
    }
}
