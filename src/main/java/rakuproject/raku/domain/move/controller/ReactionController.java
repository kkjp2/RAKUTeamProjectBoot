package rakuproject.raku.domain.move.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.move.dto.ReactionDTO;
import rakuproject.raku.domain.move.service.ReactionService;
import rakuproject.raku.domain.mypage.service.UserServiceImpl;

@RestController
@RequestMapping("/move/reactions")
@RequiredArgsConstructor
public class ReactionController {

    @Autowired
    private final ReactionService reactionService;

    @Autowired
    private UserServiceImpl userService;

    //http://localhost:8080/move/reactions/1?reactionType=like //or dislike
    @PostMapping("/{reviewId}")
    public ResponseEntity<?> reactToReview(
            @PathVariable Long reviewId,
            @RequestParam String reactionType,
            @RequestHeader("Authorization") String authorizationHeader) {
        // 从 SecurityContext 中获取用户的 userKey
        String token = authorizationHeader.substring(7);
        Long userKey = userService.getUserKeyFromToken(token);
        reactionService.reactToReview(reviewId,userKey, reactionType);

        return ResponseEntity.ok("반응이 성공적으로 기록되었습니다.");
    }

}
