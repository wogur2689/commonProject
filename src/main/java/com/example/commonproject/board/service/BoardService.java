package com.example.commonproject.board.service;

import com.example.commonproject.board.domain.Board;
import com.example.commonproject.board.domain.Comment;
import com.example.commonproject.board.dto.BoardRequestDto;
import com.example.commonproject.board.dto.BoardResponseDto;
import com.example.commonproject.board.dto.CommentReqDto;
import com.example.commonproject.board.dto.CommentResDto;
import com.example.commonproject.board.repository.BoardRepository;
import com.example.commonproject.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private static final int size = 10; //한 페이지당 보여질 게시글 갯수

    //create
    public BoardResponseDto boardSave(BoardRequestDto boardRequestDTO) {
        Board board = boardRepository.save(boardRequestDTO.toEntity(boardRequestDTO));
        return BoardResponseDto.toDto(board);
    }

    //list
    @Transactional(readOnly = true)
    public Page<BoardResponseDto> boardList(Pageable pageable) {
        //페이징
        Page<Board> boardPage = boardRepository.findAll(pageable);
        List<BoardResponseDto> boardDto = new ArrayList<>();
        for (Board board : boardPage) {
            BoardResponseDto result = BoardResponseDto.toDto(board);
            boardDto.add(result);
        }

        return new PageImpl<>(boardDto, pageable, boardPage.getTotalElements());
    }

    //search list
    public Page<BoardResponseDto> searchBoardList(Pageable pageable, String searchType, String searchKeyword) {
        //페이징
        Page<Board> boardPage = boardRepository.findAll(pageable);

        // 작성자로 검색
        if ("writer".equals(searchType)) {
            boardPage = boardRepository.findByWriterContaining(searchKeyword, pageable);
        }

        // 제목으로 검색
        if ("title".equals(searchType)) {
            boardPage = boardRepository.findByTitleContaining(searchKeyword, pageable);
        }

        //리스트로 변환
        List<BoardResponseDto> boardDto = new ArrayList<>();
        for (Board board : boardPage) {
            BoardResponseDto result = BoardResponseDto.toDto(board);
            boardDto.add(result);
        }

        return new PageImpl<>(boardDto, pageable, boardPage.getTotalElements());
    }

    //view
    @Transactional(readOnly = true)
    public BoardResponseDto boardView(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        return BoardResponseDto.toDto(board);
    }

    //update
    public BoardResponseDto boardUpdate(BoardRequestDto boardRequestDTO) {
        Board board = boardRepository.findById(boardRequestDTO.getId()).orElseThrow();
        //변경감지
        board.BoardUpdate(boardRequestDTO);
        return BoardResponseDto.toDto(board);
    }

    //delete
    public void boardDelete(BoardRequestDto boardRequestDTO) {
        boardRepository.deleteById(boardRequestDTO.getId());
    }

}
