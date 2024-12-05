package com.example.board_test.board.controller;


import com.example.board_test.board.service.BoardService;
import com.example.board_test.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final BoardService boardService;
    private final CommentService commentService;

//    @PostMapping
//    public ResponseEntity register(@RequestBody CommentRequestDTO commentRequestDTO)
//    {
//        commentService.register(commentRequestDTO);
//        return ResponseEntity.ok().build();
//    }
}
