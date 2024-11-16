package rakuproject.raku.domain.move.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.move.dto.MoveReviewDTO;
import rakuproject.raku.domain.move.dto.ReviewWithCompanyDTO;
import rakuproject.raku.domain.move.entity.MoveReviewEntity;
import rakuproject.raku.domain.move.service.MoveReviewService;

import java.util.List;

@RestController
@RequestMapping("/move/review")
public class MoveReviewController {

    @Autowired
    private MoveReviewService moveReviewService;

    // 添加评论
    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody @Valid MoveReviewDTO dto) {
        if (dto.getCompanyId() == null) {
            return ResponseEntity.badRequest().body("Company ID cannot be null");
        }
        moveReviewService.addComment(dto);
        return ResponseEntity.status(201).body("评论已成功提交！");
    }

    // 获取某公司的所有评论
    @GetMapping("/{companyId}")
    public ResponseEntity<List<MoveReviewEntity>> getCommentsByCompanyId(@PathVariable Long companyId) {
        List<MoveReviewEntity> comments = moveReviewService.getCommentsByCompanyId(companyId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/reviews")
    public List<ReviewWithCompanyDTO> getAllReviewsWithCompanyName(@RequestParam Long userKey) {
        return moveReviewService.getAllReviewsWithCompanyName(userKey);
    }
}

