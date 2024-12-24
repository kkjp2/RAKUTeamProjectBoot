package rakuproject.raku.domain.move.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.move.dto.MoveReviewDTO;
import rakuproject.raku.domain.move.dto.ReviewWithCompanyDTO;
import rakuproject.raku.domain.move.entity.MoveReviewEntity;
import rakuproject.raku.domain.move.repository.MoveReviewRepository;
import rakuproject.raku.domain.move.service.MoveReviewService;
import rakuproject.raku.domain.mypage.service.UserServiceImpl;

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

    @Autowired
    private UserServiceImpl userService;

    // 리뷰 추가.
    @PostMapping("/register")
    public ResponseEntity<?> addComment(@RequestHeader("Authorization") String authorizationHeader ,@RequestBody @Valid MoveReviewDTO dto) {
        String token = authorizationHeader.substring(7);
        Long userKey = userService.getUserKeyFromToken(token);
        dto.setUserKey(userKey);
        if (dto.getCompanyId() == null) {
            return ResponseEntity.badRequest().body("해당회사 아이디 없음,");
        }
        try {
            moveReviewService.addComment(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("리뷰가 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            // 捕获异常并返回失败信息
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("리뷰 등록 중 오류가 발생했습니다.");
        }
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

