package com.example.commonproject.board.dto;

import com.example.commonproject.board.domain.Board;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponseDTO {
    private Long id;
    private String nickName;    //유저 닉네임(작성자)
    private String title;       //제목
    private String content;     //컨텐츠

    //entity -> dto
    public static BoardResponseDTO toDto(Board board) {
        return BoardResponseDTO.builder()
                .id(board.getId())
                .nickName(board.getNickName())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }
}
