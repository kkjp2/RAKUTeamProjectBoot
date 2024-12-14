package com.example.board_test.domain.board.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDTO {
    private Long c_id;
    private String content;


}
