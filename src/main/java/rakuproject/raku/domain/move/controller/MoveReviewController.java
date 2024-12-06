package rakuproject.raku.domain.move.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.move.dto.MoveReviewDTO;
import rakuproject.raku.domain.move.dto.ReviewWithCompanyDTO;
import rakuproject.raku.domain.move.entity.MoveReviewEntity;
import rakuproject.raku.domain.move.repository.MoveReviewRepository;
import rakuproject.raku.domain.move.service.MoveReviewService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/move/review")
@RequiredArgsConstructor
@Slf4j
public class MoveReviewController {

    @Autowired
    private final MoveReviewService moveReviewService;

    @Autowired
    private final MoveReviewRepository moveReviewRepository;

    // 리뷰 추가.
    @PostMapping("/register")
    public ResponseEntity<?> addComment(@RequestBody @Valid MoveReviewDTO dto) {
        if (dto.getCompanyId() == null) {
            return ResponseEntity.badRequest().body("해당회사 아이디 없음,");
        }
        moveReviewService.addComment(dto);
        return ResponseEntity.status(201).body("리뷰 성공！");
    }

    //지정 회사의 리뷰 가져오기.
    @GetMapping("/{companyId}")
    public ResponseEntity<List<MoveReviewEntity>> getCommentsByCompanyId(@PathVariable Long companyId) {
        List<MoveReviewEntity> comments = moveReviewService.getCommentsByCompanyId(companyId);
        return ResponseEntity.ok(comments);
    }

    //모든 리뷰 가져오기.
    @GetMapping("/reviews")
    public ResponseEntity<List<MoveReviewEntity>> getAllReviews() {
        List<MoveReviewEntity> reviews = moveReviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    //리뷰 및 회사 logo 가져오기
    @GetMapping("/logo")
    public ResponseEntity<List<ReviewWithCompanyDTO>> getReviewsWithLogos() {
        List<ReviewWithCompanyDTO> reviews = moveReviewRepository.fetchReviewsWithLogos();
        return ResponseEntity.ok(reviews == null ? new ArrayList<>() : reviews);
    }

}

