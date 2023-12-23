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
    public ModelAndView boardCreate(@RequestBody @Valid BoardRequestDto boardRequestDTO, ModelAndView mav) {
        mav.addObject("board", boardService.boardSave(boardRequestDTO));
        ModelAndViewUtil.setJsonReturn(mav, ResponseCode.CODE_0000.getCode());
        return mav;
    }

    /**
     * 게시글 수정
     */
    @PostMapping("/update")
    public ModelAndView boardUpdate(@RequestBody @Valid BoardRequestDto boardRequestDTO, ModelAndView mav) {
        mav.addObject("board", boardService.boardUpdate(boardRequestDTO));
        ModelAndViewUtil.setJsonReturn(mav, ResponseCode.CODE_0000.getCode());
        return mav;
    }

    /**
     * 게시글 삭제
     */
    @PostMapping("/delete")
    public ModelAndView boardDelete(@RequestBody @Valid BoardRequestDto boardRequestDTO, ModelAndView mav) {
        boardService.boardDelete(boardRequestDTO);
        ModelAndViewUtil.setJsonReturn(mav,ResponseCode.CODE_0000.getCode());
        return mav;
    }

    /**
     * 댓글 작성
     */
    @PostMapping("/createComment")
    public ModelAndView createComment(@RequestBody @Valid CommentReqDto commentReqDTO, ModelAndView mav) {
        boardService.createComment(commentReqDTO);
        ModelAndViewUtil.setJsonReturn(mav, ResponseCode.CODE_0000.getCode());
        return mav;
    }

    /**
     * 댓글 작성
     */
    @PostMapping("/readComment")
    public ModelAndView readComment(@RequestBody @Valid CommentReqDto commentReqDTO, ModelAndView mav) {
        mav.addObject("comment", boardService.readComment(commentReqDTO, 10));
        ModelAndViewUtil.setJsonReturn(mav, ResponseCode.CODE_0000.getCode());
        return mav;
    }

    /**
     * 댓글 수정
     */
    @PostMapping("/updateComment")
    public ModelAndView updateComment(@RequestBody @Valid CommentReqDto commentReqDTO, ModelAndView mav) {
        boardService.updateComment(commentReqDTO);
        ModelAndViewUtil.setJsonReturn(mav, ResponseCode.CODE_0000.getCode());
        return mav;
    }

    /**
     * 댓글 삭제
     */
    @PostMapping("/deleteComment")
    public ModelAndView deleteComment(@RequestBody @Valid CommentReqDto commentReqDTO, ModelAndView mav) {
        boardService.deleteComment(commentReqDTO);
        ModelAndViewUtil.setJsonReturn(mav, ResponseCode.CODE_0000.getCode());
        return mav;
    }
}