package com.example.commonproject.board.dto;

import com.example.commonproject.board.domain.Comment;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResDTO {

    private Long id;
    private String firstCommentId; //첫 댓글 id (2023-10-31)
    private String writer;
    private String content;
    private Long boardId;

    //entity -> dto
    public static CommentResDTO toDto(Comment comment) {
        return CommentResDTO.builder()
                .id(comment.getId())
                .firstCommentId(comment.getFirstCommentId())
                .writer(comment.getWriter())
                .content(comment.getContent())
                .boardId(comment.getBoard().getId())
                .build();
    }
}