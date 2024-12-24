package rakuproject.raku.domain.move.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewWithReactionsDTO {
    private Long reviewId;
    private String userReaction;  // 当前用户的反应类型 (like, dislike, none)
    private Long likeCount;       // 点赞数量
    private Long dislikeCount;    // 点踩数量
}

