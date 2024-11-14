package com.example.board_test.board.controller;


import com.example.board_test.board.dto.request.BoardRequestDTO;
import com.example.board_test.board.dto.response.BoardResponseDTO;
import com.example.board_test.board.service.BoardService;
import com.example.board_test.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Long> createBoard(@RequestBody BoardRequestDTO boardRequestDTO, @RequestParam String nickname)
    {
        Long boardId= boardService.createBoard(boardRequestDTO,nickname);
        return ResponseEntity.ok(boardId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDTO> getBoardById(@PathVariable Long id)
    {
        BoardResponseDTO board=boardService.findById(id);
        return ResponseEntity.ok(board);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDTO>> getAllBoards()
    {
        List<BoardResponseDTO> boards= boardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Long> updateBoard()

}
