package com.example.commonproject.board.domain;

import com.example.commonproject.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

/**
 * 댓글 entity
 */
@Getter
@Entity
@Table(name = "comment")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstCommentId; //첫 댓글 id

    @Column(nullable = false)
    private String writer; //작성자

    @Column(nullable = false)
    private String content; //내용

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="board_id")
    private Board board;

    //변경감지
    public void CommentUpdate(String content) {
        this.content = content;
    }
}
