package com.example.commonproject.board.controller;

import com.example.commonproject.board.dto.BoardRequestDto;
import com.example.commonproject.board.dto.CommentReqDto;
import com.example.commonproject.board.service.BoardService;
import com.example.commonproject.common.util.ModelAndViewUtil;
import com.example.commonproject.common.util.ResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView boardCreate(@RequestBody @Valid BoardRequestDto boardRequestDTO) {
        return ModelAndViewUtil.setJsonDataReturn( ResponseCode.CODE_0000.getCode(), "board", boardService.boardSave(boardRequestDTO));
    }

    /**
     * 게시글 수정
     */
    @PostMapping("/update")
    public ModelAndView boardUpdate(@RequestBody @Valid BoardRequestDto boardRequestDTO) {
        return ModelAndViewUtil.setJsonDataReturn( ResponseCode.CODE_0000.getCode(), "board", boardService.boardUpdate(boardRequestDTO));
    }

    /**
     * 게시글 삭제
     */
    @PostMapping("/delete")
    public ModelAndView boardDelete(@RequestBody @Valid BoardRequestDto boardRequestDTO) {
        boardService.boardDelete(boardRequestDTO);
        return ModelAndViewUtil.setJsonReturn(ResponseCode.CODE_0000.getCode());
    }
}