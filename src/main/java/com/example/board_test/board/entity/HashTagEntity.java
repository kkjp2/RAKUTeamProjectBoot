package com.example.board_test.board.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notice_hashtag")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class HashTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hashId;

    @Column(nullable = false,unique = true)
    private String keyword;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int count; // 검색 횟수를 저장할 필드, 기본값은 0

}
