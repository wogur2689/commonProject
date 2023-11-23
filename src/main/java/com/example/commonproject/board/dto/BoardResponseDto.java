package com.example.commonproject.board.dto;

import com.example.commonproject.board.domain.Board;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponseDto {
    private Long id;
    private String writer;    //유저 닉네임(작성자)
    private String category;    //카테고리
    private String title;       //제목
    private String content;     //컨텐츠

    //entity -> dto
    public static BoardResponseDto toDto(Board board) {
        return BoardResponseDto.builder()
                .id(board.getId())
                .writer(board.getWriter())
                .category(board.getCategory())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }
}
