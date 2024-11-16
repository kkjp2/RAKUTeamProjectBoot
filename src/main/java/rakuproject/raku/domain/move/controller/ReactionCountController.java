package rakuproject.raku.domain.move.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.member.dto.MemberDTO;
import rakuproject.raku.domain.move.dto.ReactionCountResponse;
import rakuproject.raku.domain.move.entity.MoveReviewEntity;
import rakuproject.raku.domain.move.repository.MoveReviewRepository;
import rakuproject.raku.domain.move.service.ReviewReactionCountService;

@RestController
@RequestMapping("/move/reactionCounts")
public class ReactionCountController {

    @Autowired
    private MoveReviewRepository moveReviewRepository; // 确保注入

    @Autowired
    private ReviewReactionCountService reactionCountService;

    // 获取某评论的点赞和点踩数量
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReactionCountResponse> getReactionCount(
            @PathVariable Long reviewId,
            @RequestParam Long userKey
    ) {
        ReactionCountResponse count = reactionCountService.getReactionCount(reviewId, userKey);
        return ResponseEntity.ok(count);
    }

}
