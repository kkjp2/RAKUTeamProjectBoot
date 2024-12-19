package rakuproject.raku.domain.board.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.board.dto.request.BoardRegisterRequest;
import rakuproject.raku.domain.board.dto.request.ImageRequestDTO;
import rakuproject.raku.domain.board.dto.response.BoardResponseDTO;
import rakuproject.raku.domain.board.service.BoardService;
import rakuproject.raku.domain.board.service.ImageService;

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



    @GetMapping("/category/{category}")
    public ResponseEntity<List<BoardResponseDTO>> getBoardsByCategory(@PathVariable("category") int category) {
        System.out.println(category);
        if (category < 1 || category > 8) {
            return ResponseEntity.badRequest().body(null);  // 잘못된 카테고리 번호인 경우 400 응답
        }
        List<BoardResponseDTO> boards = boardService.findBoardsByCategory(category);
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
