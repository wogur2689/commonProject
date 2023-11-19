package com.example.commonproject.board.service;

import com.example.commonproject.board.domain.Board;
import com.example.commonproject.board.domain.Comment;
import com.example.commonproject.board.dto.BoardRequestDTO;
import com.example.commonproject.board.dto.BoardResponseDTO;
import com.example.commonproject.board.dto.CommentReqDTO;
import com.example.commonproject.board.dto.CommentResDTO;
import com.example.commonproject.board.repository.BoardRepository;
import com.example.commonproject.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

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
        Board board = boardRepository.findById(id).orElseThrow();
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

    //댓글 추가
    public CommentResDTO addComment(CommentReqDTO commentReqDTO) {
        Comment comment = commentRepository.save(commentReqDTO.toEntity(commentReqDTO));
        return CommentResDTO.toDto(comment);
    }

    //댓글 가져오기
    public CommentResDTO getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        return CommentResDTO.toDto(comment);
    }

    //댓글 수정
    public CommentResDTO updateComment(CommentReqDTO commentReqDTO) {
        Comment comment = commentRepository.findById(commentReqDTO.getId())
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentReqDTO.getId()));
        //변경감지
        comment.CommentUpdate(comment.getContent());
        return CommentResDTO.toDto(comment);
    }

    //댓글 삭제
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
