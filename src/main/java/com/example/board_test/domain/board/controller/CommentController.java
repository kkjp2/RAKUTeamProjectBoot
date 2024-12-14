package com.example.board_test.domain.board.controller;


import com.example.board_test.domain.board.dto.request.CommentDTO;
import com.example.board_test.domain.board.dto.request.CommentRequestDTO;
import com.example.board_test.domain.board.dto.response.CommentResponseDTO;
import com.example.board_test.domain.board.service.BoardService;
import com.example.board_test.domain.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class CommentController {
    private final CommentService commentService;
    private final BoardService boardService;

    @PostMapping("/{boardId}/comments")
    public ResponseEntity register(@PathVariable("boardId") Long id,
            @RequestBody CommentRequestDTO commentRequestDTO)
    {
        commentService.register(commentRequestDTO, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{commId}")
    public ResponseEntity updateComment(
            @PathVariable("commId") Long cid,
                                        @RequestBody CommentRequestDTO commentRequestDTO)
    {
        commentRequestDTO.setComment_id(cid);
        commentService.update(commentRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commId}")
    public ResponseEntity deleteComment(@PathVariable("commId") Long cid)
    {
        commentService.deleteComment(cid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<List<CommentDTO>> getAllComments(@PathVariable("boardId")Long bid)
    {
        List<CommentDTO> comments=commentService.getCommentByBoardId(bid);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/comment/{commId}")
    public ResponseEntity findByCommentId(@PathVariable("commId")Long cid)
    {
        CommentResponseDTO commentResponseDTO=commentService.findById(cid);
        return ResponseEntity.ok(commentResponseDTO);




    }




}
