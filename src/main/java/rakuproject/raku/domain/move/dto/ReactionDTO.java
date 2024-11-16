package rakuproject.raku.domain.move.dto;

import lombok.*;
import rakuproject.raku.domain.move.entity.enums.ReactionType;

@Getter
@Setter
public class ReactionDTO {
    private Long reviewId;
    private ReactionType reactionType;
    private int reactionValue;

    public ReactionDTO(Long reviewId, ReactionType reactionType) {
        this.reviewId = reviewId;
        this.reactionType = reactionType;
    }
}
