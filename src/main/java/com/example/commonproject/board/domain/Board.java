package com.example.commonproject.board.domain;

import com.example.commonproject.board.dto.BoardRequestDto;
import com.example.commonproject.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 게시판 entity
 */
@Getter
@Entity
@Table(name = "board")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "writer")
    private String writer;    //유저 닉네임(작성자)

    @Column(name = "title")
    private String title;       //제목

    @Column(name = "content")
    private String content;     //컨텐츠

    //변경감지(수정)
    public void BoardUpdate(BoardRequestDto boardRequestDTO) {
        this.writer = boardRequestDTO.getWriter();
        this.title = boardRequestDTO.getTitle();
        this.content = boardRequestDTO.getContent();
    }
}
