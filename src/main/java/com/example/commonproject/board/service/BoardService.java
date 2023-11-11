package com.example.commonproject.board.service;

import com.example.commonproject.board.domain.Board;
import com.example.commonproject.board.dto.BoardRequestDTO;
import com.example.commonproject.board.dto.BoardResponseDTO;
import com.example.commonproject.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    //create
    public BoardResponseDTO boardSave(BoardRequestDTO boardRequestDTO) {
        Board board = boardRepository.save(boardRequestDTO.toEntity(boardRequestDTO));
        return BoardResponseDTO.toDto(board);
    }

    //list
    @Transactional(readOnly = true)
    public List<BoardResponseDTO> boardList() {
        List<Board> boardList = boardRepository.findAll();
        return boardList.stream()
                .map(BoardResponseDTO::toDto)
                .toList();
    }

    //view
    @Transactional(readOnly = true)
    public BoardResponseDTO boardView(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow();
        return BoardResponseDTO.toDto(board);
    }

    //update
    public BoardResponseDTO boardUpdate(BoardRequestDTO boardRequestDTO) {
        Board board = boardRepository.findById(boardRequestDTO.getId()).orElseThrow();
        //변경감지
        board.BoardUpdate(boardRequestDTO);
        return BoardResponseDTO.toDto(board);
    }

    //delete
    public void boardDelete(BoardRequestDTO boardRequestDTO) {
        boardRepository.deleteById(boardRequestDTO.getId());
    }
}
