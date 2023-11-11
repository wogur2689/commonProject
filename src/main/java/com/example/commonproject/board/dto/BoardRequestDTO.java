package com.example.commonproject.board.dto;

import com.example.commonproject.board.domain.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDTO {
    private Long id;
    private String nickName;    //유저 닉네임(작성자)
    private String title;       //제목
    private String content;     //컨텐츠

    //dto -> entity
    public Board toEntity(BoardRequestDTO boardRequestDTO) {
        return Board.builder()
                .nickName(boardRequestDTO.nickName)
                .title(boardRequestDTO.title)
                .content(boardRequestDTO.content)
                .build();
    }
}
