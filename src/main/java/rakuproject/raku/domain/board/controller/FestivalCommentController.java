package rakuproject.raku.domain.board.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.board.dto.request.FestivalCommentDTO;
import rakuproject.raku.domain.board.dto.request.FestivalCommentRequestDTO;
import rakuproject.raku.domain.board.dto.response.FestivalCommentResponseDTO;
import rakuproject.raku.domain.board.service.FestivalBoardService;
import rakuproject.raku.domain.board.service.FestivalCommentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/festivals")
public class FestivalCommentController {
    private final FestivalCommentService festivalCommentService;
    private final FestivalBoardService festivalBoardService;

    @PostMapping("/{festivalId}/comments")
    public ResponseEntity register(@PathVariable("festivalId") Long id,
                                   @RequestBody FestivalCommentRequestDTO festivalCommentRequestDTO)
    {
        festivalCommentService.register(festivalCommentRequestDTO,id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{fcId}")
    public ResponseEntity updateFComment
            (@PathVariable("fcId") Long fid,
             @RequestBody FestivalCommentRequestDTO festivalCommentRequestDTO)
    {
        festivalCommentRequestDTO.setFc_id(fid);
        festivalCommentService.update(festivalCommentRequestDTO);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{fcId}")
    public ResponseEntity deleteFestivalComment(@PathVariable("fcId") Long fc_id)
    {
        festivalCommentService.delete(fc_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{festivalBoardId}")
    public ResponseEntity<List<FestivalCommentDTO>> getAllFCComments
            (@PathVariable("festivalBoardId")Long fid)
    {
        List<FestivalCommentDTO> festivalComments=
                festivalCommentService.getFestivalCommentByFestivalId(fid);
        return ResponseEntity.ok(festivalComments);
    }
    @GetMapping("/festivalcomment/{fcId}")
    public ResponseEntity findByFCommentId(@PathVariable("fcId")Long fc_id)
    {
        FestivalCommentResponseDTO festivalCommentResponseDTO=
                festivalCommentService.findById(fc_id);
        return ResponseEntity.ok(festivalCommentResponseDTO);
    }



}
