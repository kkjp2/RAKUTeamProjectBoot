package rakuproject.raku.domain.move.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.move.service.ReactionService;

@RestController
@RequestMapping("/move/reactions")
@RequiredArgsConstructor
public class ReactionController {

    @Autowired
    private final ReactionService reactionService;

    //http://localhost:8080/move/reactions/1?reactionType=like //or dislike
    @PostMapping("/{reviewId}")
    public ResponseEntity<String> reactToReview(
            @PathVariable Long reviewId,
            @RequestParam String reactionType,
            Authentication authentication) {
        // 从 SecurityContext 中获取用户的 userKey
        String userKey = authentication.getName();

        reactionService.reactToReview(reviewId, userKey, reactionType);

        return ResponseEntity.ok("반응이 성공적으로 기록되었습니다.");
    }
}
