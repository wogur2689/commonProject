package com.example.commonproject.board.controller;

import com.example.commonproject.board.dto.BoardRequestDto;
import com.example.commonproject.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    /**
     * 리스트 화면(페이징)
     */
    @GetMapping("/list")
    public ModelAndView board(BoardRequestDto boardRequestDto, ModelAndView mav) {
        mav.setViewName("board/list");
        // 검색 조건이 있는 경우 처리
        if (boardRequestDto.getSearchType() != null && boardRequestDto.getSearchKeyword() != null) {
            mav.addObject("list", boardService.searchBoardList(
                    boardRequestDto.getPage(),
                    boardRequestDto.getSort(),
                    boardRequestDto.getSearchType(),
                    boardRequestDto.getSearchKeyword()
            ));

            return mav;
        }

        mav.addObject("list", boardService.boardList(boardRequestDto.getPage(), boardRequestDto.getSort()));
        return mav;
    }

    /**
     * 게시판 작성
     */
    @GetMapping("/write")
    public ModelAndView boardWrite(ModelAndView mav) {
        mav.setViewName("board/write");
        return mav;
    }

    /**
     * 게시판 수정
     */
    @GetMapping("/edit/{id}")
    public ModelAndView boardEdit(@PathVariable Long id, ModelAndView mav) {
        mav.addObject("data", boardService.boardView(id));
        mav.setViewName("board/edit");
        return mav;
    }

    /**
     * 게시글 보기
     */
    @GetMapping("/view/{id}")
    public ModelAndView boardView(@PathVariable Long id, ModelAndView mav) {
        mav.addObject("data", boardService.boardView(id));
        mav.setViewName("board/view");
        return mav;
    }
}
