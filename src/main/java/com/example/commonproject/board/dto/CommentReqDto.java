package com.example.commonproject.board.dto;

import com.example.commonproject.board.domain.Board;
import com.example.commonproject.board.domain.Comment;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentReqDto {

    private Long id;
    private String firstCommentId; //첫 댓글 id
    private String writer;
    private String content;
    private Long boardId;

    //dto -> entity
    public Comment toEntity(CommentReqDto commentReqDTO, Board board) {
        return Comment.builder()
                .firstCommentId(commentReqDTO.getFirstCommentId())
                .writer(commentReqDTO.getWriter())
                .content(commentReqDTO.getContent())
                .board(board)
                .build();
    }
}