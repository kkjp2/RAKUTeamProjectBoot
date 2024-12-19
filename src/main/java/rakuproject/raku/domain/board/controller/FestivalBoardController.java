package rakuproject.raku.domain.board.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.board.dto.request.FestivalRegisterRequest;
import rakuproject.raku.domain.board.dto.request.ImageRequestDTO;
import rakuproject.raku.domain.board.dto.response.FestivalBoardResponseDTO;
import rakuproject.raku.domain.board.service.FestivalBoardService;
import rakuproject.raku.domain.board.service.ImageService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/festival")
public class FestivalBoardController {
    private final FestivalBoardService festivalBoardService;
    private final ImageService imageService;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admin")
    public ResponseEntity register(@RequestBody FestivalRegisterRequest request)
    {
        festivalBoardService.register(request);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity findFestivalById(@PathVariable("id")Long id)
    {
        FestivalBoardResponseDTO festival=festivalBoardService.findById(id);
        return ResponseEntity.ok(festival);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/admin/{id}")
    public ResponseEntity updateFestival(@PathVariable("id")Long id, @RequestBody FestivalRegisterRequest request)
    {
        request.setId(id);
        festivalBoardService.update(request);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<FestivalBoardResponseDTO>> getAllFestivals()
    {
        List<FestivalBoardResponseDTO> festivals=festivalBoardService.getAllFestivals();
        return ResponseEntity.ok(festivals);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteFestival(@PathVariable("id") Long id)
    {
        festivalBoardService.deleteFestival(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{id}/images")
    public ResponseEntity<Long> addImageToFestival(@PathVariable("id")Long id, @RequestBody ImageRequestDTO imageRequestDTO)
    {
        imageRequestDTO.setFestivalBoardId(id);
        Long imageId=imageService.addImage(imageRequestDTO);
        return ResponseEntity.ok(imageId);
    }




}
