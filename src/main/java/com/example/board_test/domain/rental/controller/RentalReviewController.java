package com.example.board_test.domain.rental.controller;

import com.example.board_test.domain.rental.dto.RentalReviewDTO;
import com.example.board_test.domain.rental.dto.response.RentalReviewResponseDTO;
import com.example.board_test.domain.rental.service.RentalReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class RentalReviewController {

    @Autowired
    private final RentalReviewService rentalReviewService;


    @PostMapping
    public ResponseEntity<Void> register(@RequestBody RentalReviewDTO reviewDTO)
    {
        rentalReviewService.register(reviewDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<RentalReviewResponseDTO> getReview(@PathVariable Long reviewId)
    {
        return ResponseEntity.ok(rentalReviewService.getReviewById(reviewId));
    }

    @GetMapping("/rental/board/{rbId}")
    public ResponseEntity<List<RentalReviewResponseDTO>> getReviewsByRentalBoardId(@PathVariable Long rbId)
    {
        return ResponseEntity.ok(rentalReviewService.getReviewsByRentalBoardId(rbId));
    }

    @PutMapping
    public ResponseEntity<Void> updateReview(@RequestBody RentalReviewDTO reviewDTO)
    {
        rentalReviewService.updateReview(reviewDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId)
    {
        rentalReviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

}
