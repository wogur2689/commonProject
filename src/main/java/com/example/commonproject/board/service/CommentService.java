package com.example.commonproject.board.service;

import com.example.commonproject.board.domain.Board;
import com.example.commonproject.board.domain.Comment;
import com.example.commonproject.board.dto.BoardResponseDto;
import com.example.commonproject.board.dto.CommentReqDto;
import com.example.commonproject.board.dto.CommentResDto;
import com.example.commonproject.board.repository.BoardRepository;
import com.example.commonproject.board.repository.CommentRepository;
import com.example.commonproject.common.util.ModelAndViewUtil;
import com.example.commonproject.common.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private static final int size = 5; //한 페이지당 보여질 게시글 갯수
    //댓글 추가
    public CommentResDto createComment(CommentReqDto commentReqDTO) {
        Board board = boardRepository.findById(commentReqDTO.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판이 존재하지 않습니다."));

        Comment comment = commentRepository.save(commentReqDTO.toEntity(commentReqDTO, board));
        return CommentResDto.toDto(comment);
    }

    //댓글 가져오기
    @Transactional(readOnly = true)
    public List<CommentResDto> readComment() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentResDto> commentResDto = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResDto result = CommentResDto.toDto(comment);
            commentResDto.add(result);
        }

        return commentResDto;
    }

    //댓글 수정
    public CommentResDto updateComment(CommentReqDto commentReqDTO) {
        Comment comment = commentRepository.findById(commentReqDTO.getId())
                .orElseThrow(() -> new RuntimeException("error "));
        //변경감지
        comment.CommentUpdate(comment.getContent());
        return CommentResDto.toDto(comment);
    }

    //댓글 삭제
    public void deleteComment(CommentReqDto commentReqDTO) {
        commentRepository.deleteById(commentReqDTO.getId());
    }
}
