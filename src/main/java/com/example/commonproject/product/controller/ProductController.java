package com.example.commonproject.product.controller;

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
@RequestMapping("/product")
public class ProductController {
    private final BoardService boardService;

    /**
     * 상품 리스트 화면(페이징 적용)
     */
    @GetMapping("/list")
    public ModelAndView board(
            @RequestParam(defaultValue = "1") int page,
            ModelAndView mav) {
        mav.addObject("list", boardService.boardList(page));
        mav.setViewName("board/list");
        return mav;
    }

    /**
     * (admin) 상품 등록
     */
    @GetMapping("/write")
    public ModelAndView boardWrite(ModelAndView mav) {
        mav.setViewName("board/write");
        return mav;
    }

    /**
     * (admim) 수정
     */
    @GetMapping("/edit/{id}")
    public ModelAndView boardEdit(@PathVariable(name = "id") Long id, ModelAndView mav) {
        mav.addObject("data", boardService.boardView(id));
        mav.setViewName("board/edit");
        return mav;
    }

    /**
     * (admim) 상품 자세히 보기
     */
    @GetMapping("/view/{id}")
    public ModelAndView boardView(@PathVariable(name = "id") Long id, ModelAndView mav) {
        mav.addObject("data", boardService.boardView(id));
        mav.setViewName("board/view");
        return mav;
    }
}
