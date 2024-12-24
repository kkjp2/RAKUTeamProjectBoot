package rakuproject.raku.domain.move.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuproject.raku.domain.move.dto.ReactionCountResponse;
import rakuproject.raku.domain.move.service.ReactionCountService;

@RestController
@RequestMapping("/move/reactionCounts")
@RequiredArgsConstructor
public class ReactionCountController {

    @Autowired
    private final ReactionCountService reactionCountService;

    //http://localhost:8080/move/reactionCounts/{reviewId}
    //지정된 리뷰의 좋아요/싫어요 수를 가져오기.
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReactionCountResponse> getReactionCount(
            @PathVariable Long reviewId
    ) {
        ReactionCountResponse count = reactionCountService.getReactionCount(reviewId);
        return ResponseEntity.ok(count);
    }
}
