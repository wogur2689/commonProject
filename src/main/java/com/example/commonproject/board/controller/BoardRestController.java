package com.example.commonproject.board.controller;

import com.example.commonproject.board.dto.BoardRequestDto;
import com.example.commonproject.board.dto.CommentReqDto;
import com.example.commonproject.board.service.BoardService;
import com.example.commonproject.common.util.ResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        mav.addObject("code", ResponseCode.CODE_0000.getCode());
        mav.addObject("msg", ResponseCode.CODE_0000.getMsg());
        mav.setViewName("jsonView");
        return mav;
    }

    /**
     * 게시글 수정
     */
    @PostMapping("/update")
    public ModelAndView boardUpdate(@RequestBody @Valid BoardRequestDto boardRequestDTO, ModelAndView mav) {
        mav.addObject("board", boardService.boardUpdate(boardRequestDTO));
        mav.addObject("code", ResponseCode.CODE_0000.getCode());
        mav.addObject("msg", ResponseCode.CODE_0000.getMsg());
        mav.setViewName("jsonView");
        return mav;
    }

    /**
     * 게시글 삭제
     */
    @PostMapping("/delete")
    public ModelAndView boardDelete(@RequestBody @Valid BoardRequestDto boardRequestDTO, ModelAndView mav) {
        boardService.boardDelete(boardRequestDTO);
        mav.addObject("code", ResponseCode.CODE_0000.getCode());
        mav.addObject("msg", ResponseCode.CODE_0000.getMsg());
        mav.setViewName("jsonView");
        return mav;
    }

    //댓글 작성
    @PostMapping("/addComment")
    public String addComment(@ModelAttribute @Valid CommentReqDto commentReqDTO, RedirectAttributes attributes) {
        boardService.addComment(commentReqDTO);

        return "redirect:/adviceboard/view/{id}";
    }

//    @PostMapping("/updateComment")
//    public String updateComment(@ModelAttribute @Valid CommentReqDTO commentReqDTO, RedirectAttributes attributes) {
//        Comment comment = modelMapper.map(commentReqDTO, Comment.class);
//        AdviceBoard adviceBoard = new AdviceBoard();
//        adviceBoard.setId(commentReqDTO.getAdviceboardId());
//        comment.setAdviceboards(adviceBoard);
//        commentService.updateComment(comment);
//        attributes.addAttribute("id", comment.getAdviceboards().getId());
//        return "redirect:/adviceboard/view/{id}";
//    }
}