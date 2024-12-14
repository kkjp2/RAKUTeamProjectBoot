package com.example.board_test.domain.board.controller;


import com.example.board_test.domain.board.dto.request.BoardRegisterRequest;
import com.example.board_test.domain.board.dto.request.ImageRequestDTO;
import com.example.board_test.domain.board.dto.response.BoardResponseDTO;
import com.example.board_test.domain.board.service.BoardService;
import com.example.board_test.domain.board.service.ImageService;
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
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity register(@RequestBody BoardRegisterRequest request)
    {
        boardService.register(request);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity findBoardById(@PathVariable("id") Long id)
    {
        BoardResponseDTO board=boardService.findById(id);
        return ResponseEntity.ok(board);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBoard(@PathVariable("id") Long id, @RequestBody BoardRegisterRequest request)
    {
        request.setId(id);
        boardService.update(request);
        return ResponseEntity.ok().build();
    }



    @GetMapping
    public ResponseEntity<List<BoardResponseDTO>> getAllBoards()
    {
        List<BoardResponseDTO> boards= boardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("id") Long id)
    {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{id}/images")
    public ResponseEntity<Long> addImageToBoard(@PathVariable("id") Long id, @RequestBody ImageRequestDTO imageRequestDTO)
    {
        imageRequestDTO.setBoardId(id);
        Long imageId=imageService.addImage(imageRequestDTO);
        return ResponseEntity.ok(imageId);
    }

//    @PostMapping("/{id}/images")
//    public ResponseEntity<List<ImageResponseDTO>> getImagesByBoard(@PathVariable BoardEntity board)
//    {
//        List<ImageResponseDTO> images= imageService.getImagesByBoard(board);
//        return ResponseEntity.ok(images);
//    }


}
