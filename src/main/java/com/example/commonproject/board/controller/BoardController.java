package com.example.commonproject.board.controller;

import com.example.commonproject.board.dto.BoardRequestDto;
import com.example.commonproject.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView board(
            @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
//            @RequestParam String searchType,
//            @RequestParam String searchKeyword,
            ModelAndView mav
    ) {
        // 검색 조건이 있는 경우 처리
//        if (searchType != null && searchKeyword != null) {
//            mav.addObject("list", boardService.searchBoardList(
//                    page,
//                    sort,
//                    searchType,
//                    searchKeyword
//            ));
//            return mav;
//        }

        mav.addObject("list", boardService.boardList(pageable));
        mav.setViewName("board/list");
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
