package com.example.board_test.board.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "sample")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TestSample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long n_id;
    @Column(columnDefinition = "Text", nullable = false)
    private String content;
    @Column(length = 50,nullable = false)
    private String title;
}
