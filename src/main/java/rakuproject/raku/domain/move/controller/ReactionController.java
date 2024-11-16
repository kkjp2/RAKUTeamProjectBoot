package rakuproject.raku.domain.move.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.member.dto.MemberDTO;
import rakuproject.raku.domain.move.dto.ReactionCountResponse;
import rakuproject.raku.domain.move.dto.ReactionDTO;
import rakuproject.raku.domain.move.entity.ReactionEntity;
import rakuproject.raku.domain.move.service.ReactionService;
import rakuproject.raku.domain.move.service.ReviewReactionCountService;

import java.util.Map;

@RestController
@RequestMapping("/move/reactions")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @Autowired
    private ReviewReactionCountService reviewReactionCountService;

    @PostMapping("/{reviewId}/like")
    public ResponseEntity<ReactionCountResponse> likeReview(@PathVariable Long reviewId, @RequestBody MemberDTO userRequest) {
        reactionService.likeComment(reviewId, userRequest.getUserKey());
        ReactionCountResponse updatedCounts = reviewReactionCountService.getReactionCount(reviewId,userRequest.getUserKey());
        return ResponseEntity.ok(updatedCounts);
    }

    @PostMapping("/{reviewId}/dislike")
    public ResponseEntity<ReactionCountResponse> dislikeReview(@PathVariable Long reviewId, @RequestBody MemberDTO userRequest) {
        reactionService.dislikeComment(reviewId, userRequest.getUserKey());
        ReactionCountResponse updatedCounts = reviewReactionCountService.getReactionCount(reviewId,userRequest.getUserKey());
        return ResponseEntity.ok(updatedCounts);
    }

    @PostMapping("/{reviewId}/cancel")
    public ResponseEntity<ReactionCountResponse> cancelReaction(
            @PathVariable Long reviewId,
            @RequestParam(required = false) Long userKey,
            @RequestBody(required = false) Map<String, Long> body) {

        if (userKey == null && body != null) {
            userKey = body.get("userKey");
        }
        if (userKey == null) {
            throw new RuntimeException("userKey is required");
        }

        ReactionCountResponse response = reactionService.cancelReaction(reviewId, userKey);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{reviewId}/reaction")
    public ResponseEntity<ReactionDTO> getUserReaction(
            @PathVariable Long reviewId,
            @RequestParam Long userKey) {
        ReactionEntity reaction = reactionService.getReactionByUserAndReview(userKey, reviewId);
        if (reaction != null) {
            return ResponseEntity.ok(new ReactionDTO(reviewId, reaction.getReactionType()));
        } else {
            return ResponseEntity.ok(new ReactionDTO(reviewId, null)); // 没有反应
        }
    }



}
