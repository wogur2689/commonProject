package com.example.commonproject.product.domain;

import com.example.commonproject.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "product")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "writer")
    private String writer;      //유저 닉네임(작성자)

    @Column(name = "title")
    private String title;       //제목

    @Column(name = "category")
    private String category;    //카테고리

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;     //컨텐츠

    @Column(name = "price")
    private Integer price;      //가격

    @Column(name = "product_count")
    private Integer productCount;//상품수량

    @Column(name = "product_status")
    private ProductStatus productStatus;//상태
}
