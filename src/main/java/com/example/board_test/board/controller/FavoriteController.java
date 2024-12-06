package com.example.board_test.board.controller;


import com.example.board_test.board.dto.request.FavoriteRequestDTO;
import com.example.board_test.board.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity register(@RequestBody FavoriteRequestDTO requestDTO)
    {
        favoriteService.addFavorite(requestDTO);
        return ResponseEntity.ok().build();
    }



}
