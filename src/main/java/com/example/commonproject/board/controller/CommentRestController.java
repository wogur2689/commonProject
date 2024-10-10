package com.example.commonproject.board.controller;

import com.example.commonproject.board.dto.CommentReqDto;
import com.example.commonproject.board.service.CommentService;
import com.example.commonproject.common.util.ModelAndViewUtil;
import com.example.commonproject.common.util.ResponseCode;
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
@RequestMapping("/api/comment")
public class CommentRestController {
    
    private final CommentService commentService;
    /**
     * 댓글 작성
     */
    @PostMapping("/create")
    public ModelAndView createComment(@RequestBody @Valid CommentReqDto commentReqDTO, ModelAndView mav) {
        commentService.createComment(commentReqDTO);
        return ModelAndViewUtil.setJsonReturn( ResponseCode.CODE_0000.getCode());
    }

    /**
     * 댓글 읽기
     */
    @PostMapping("/read")
    public ModelAndView readComment(ModelAndView mav) {
        mav.addObject("comment", commentService.readComment());
        mav.setViewName("jsonView");
        return mav;
    }

    /**
     * 댓글 수정
     */
    @PostMapping("/update")
    public ModelAndView updateComment(@RequestBody @Valid CommentReqDto commentReqDTO, ModelAndView mav) {
        commentService.updateComment(commentReqDTO);
        return ModelAndViewUtil.setJsonReturn( ResponseCode.CODE_0000.getCode());
    }

    /**
     * 댓글 삭제
     */
    @PostMapping("/delete")
    public ModelAndView deleteComment(@RequestBody @Valid CommentReqDto commentReqDTO, ModelAndView mav) {
        commentService.deleteComment(commentReqDTO);
        return ModelAndViewUtil.setJsonReturn( ResponseCode.CODE_0000.getCode());
    }
}
