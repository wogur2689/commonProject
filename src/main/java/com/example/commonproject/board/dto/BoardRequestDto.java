package com.example.commonproject.board.dto;

import com.example.commonproject.board.domain.Board;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {
    private Long id;
    private String writer;    //유저 닉네임(작성자)
    private String title;       //제목
    private String category;    //카테고리
    private String content;     //컨텐츠

    //dto -> entity
    public Board toEntity(BoardRequestDto boardRequestDTO) {
        return Board.builder()
                .writer(boardRequestDTO.getWriter())
                .title(boardRequestDTO.getTitle())
                .category(boardRequestDTO.getCategory())
                .content(boardRequestDTO.getContent())
                .build();
    }
}
