package com.example.commonproject.board.dto;

import com.example.commonproject.board.domain.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResDto {

    private Long id;
    private Long firstCommentId;    //첫 댓글 id (2023-10-31)
    private String writer;          //작성자
    private String content;         //내용
    private Long boardId;           //게시글 id
    private LocalDateTime createAt; //등록일

    //entity -> dto
    public static CommentResDto toDto(Comment comment) {
        return CommentResDto.builder()
                .id(comment.getId())
                .firstCommentId(comment.getFirstCommentId())
                .writer(comment.getWriter())
                .content(comment.getContent())
                .createAt(comment.getCreatedAt())
                .boardId(comment.getBoard().getId())
                .build();
    }
}